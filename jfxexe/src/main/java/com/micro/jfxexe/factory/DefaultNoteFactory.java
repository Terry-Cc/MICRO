package com.micro.jfxexe.factory;

import com.micro.common.util.other.CommonUtils;
import com.micro.common.util.other.FileUtils;
import com.micro.jfxexe.common.NodeUtils;
import com.micro.jfxexe.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author XiongJiaMin
 * @apiNote NOTE 工厂
 * @since 2022-11-19 17:55
 **/
@SuppressWarnings("unused")
public class DefaultNoteFactory implements IStaticFactory<NoteBase> {

    private static Logger logger = LoggerFactory.getLogger(DefaultNoteFactory.class);

    private final static String DEFAULT_NOTE_PATH = "\\data\\noteHome\\";

    private final static String DEFAULT_NOTE_SUF = ".ntc";
    
    private final static String DEFAULT_NOTE_RELATION_NAME = "noteRelation.ntr";

    private final static Map<String, Note> NOTE_CACHE = new ConcurrentHashMap<>(16);

    private final static Map<String, NoteLabel> NOTE_LABEL_CACHE = new ConcurrentHashMap<>(16);

    /**
     * @since 2023/4/18 16:49
     * @description <p>
     *  笔记标签列表
     * </p>
     */
    private Map<String, NoteCollection> noteCollectionMap;

    /**
     * @since 2023/4/18 16:49
     * @description <p>
     *  关系映射
     * </p>
     */
    private NoteRelationship noteRelationship;

    /**
     * @since 2023/4/18 16:50
     * @description <p>
     *  笔记存储路径
     * </p>
     */
    private String notePath;

    /**
     * @since 2023/4/18 16:50
     * @description <p>
     *  是否开启同步
     * </p>
     */
    private boolean isSync;

    /**
     * @since 2023/4/18 16:50
     * @description <p>
     *  是否使用缓存
     * </p>
     */
    private boolean isCache;

    private DefaultNoteFactory () {
        this.initialize();
    }

    public static DefaultNoteFactory getInstance () {
        return DefaultNoteFactorySingle.INSTANCE;
    }

    private static class DefaultNoteFactorySingle {
        public static final DefaultNoteFactory INSTANCE = new DefaultNoteFactory();
    }

    /**
     * 初始化
     */
    @Override
    public void initialize () {
        logger.info("note factory ----- init start");
        this.verificationNotePath();
        if (FileUtils.creatFile(this.getNotePath())) {
            if (this.isNoteEmpty()) {
                this.setNoteCollectionMap(new ConcurrentHashMap<>(16));
            }
            if (this.noteRelationship == null) {
                this.setNoteRelationship(new NoteRelationship(LocalDateTime.now(), LocalDateTime.now(), new HashMap<>(), new HashMap<>()));
            }
            // 初始化
            this.getNoteCollectionMap().clear();
            this.getNoteRelationship().getLabelIdToNoteId().clear();
            this.getNoteRelationship().getNoteIdToLabelId().clear();
            NOTE_CACHE.clear();
            NOTE_LABEL_CACHE.clear();
            // 加载
            this.loadAllNotes();
            this.loadNoteRelation();
        } else {
            throw new IllegalArgumentException("note factory ----- initialize error, note path is not exist or not a path");
        }
        logger.info(String.join(" ", "note factory ----- init success", "note home :", FileUtils.getCanonicalPath(notePath)));
    }

    /**
     *  刷新缓存
     */
    public void flushCache () {
        NOTE_CACHE.clear();
        NOTE_LABEL_CACHE.clear();
        for (NoteCollection noteCollection : this.getNoteCollectionMap().values()) {
            for (Note note : noteCollection.getNotes().values()) {
                NOTE_CACHE.put(note.getNoteId(), note);
            }
            for (NoteLabel noteLabel : noteCollection.getNoteLabels().values()) {
                NOTE_LABEL_CACHE.put(noteLabel.getLabelId(), noteLabel);
            }
        }
        logger.info("note factory ----- note cache flush success");
    }

    /**
     * 生成一条笔记/标签, 同步开启时同步更新文件
     * @param noteBase 顶层
     */
    @Override
    public void production(NoteBase noteBase) {
        if (this.existNote(noteBase)) {
            return;
        }
        this.updateNote(noteBase);
    }

    /**
     * 强制更新/生成一条笔记/标签, 同步开启时同步更新文件
     * @param noteBase 顶层
     */
    public void updateNote (NoteBase noteBase) {
        if (noteBase instanceof Note) {
            this.production((Note) noteBase);
        } else if (noteBase instanceof NoteLabel) {
            this.production((NoteLabel) noteBase);
        }
    }

    /**
     * 消除一个笔记/标签, 同步开启同步更新文件
     * @param noteBase 顶层
     */
    @Override
    public void consumption(NoteBase noteBase) {
        if (!this.existNote(noteBase)) {
            return;
        }
        if (noteBase instanceof Note) {
            this.consumption((Note) noteBase);
        } else if (noteBase instanceof NoteLabel) {
            this.consumption((NoteLabel) noteBase);
        }
    }

    /**
     * 判断笔记或者标签是否存在
     */
    public boolean existNote(NoteBase noteBase) {
        return getNoteBase(noteBase) != null;
    }

    /**
     * 用ID获取一条笔记
     */
    public Note getNoteById (String noteId) {
        Note note = new Note();
        note.setNoteId(noteId);
        return (Note) getNoteBase(note);
    }

    /**
     * 用ID获取一条标签
     */
    public NoteLabel getNoteLabelById (String labelId) {
        NoteLabel label = new NoteLabel();
        label.setLabelId(labelId);
        return (NoteLabel) getNoteBase(label);
    }

    /**
     * 获取一条笔记或者标签
     */
    public NoteBase getNoteBase (NoteBase noteBase) {
        if (noteBase instanceof Note) {
            Note note = (Note) noteBase;
            if (CommonUtils.isEmpty(note.getNoteId())) {
                if (!CommonUtils.isEmpty(note.getNoteText())) {
                    note.setNoteId(NodeUtils.creatId(note.getNoteText()));
                } else {
                    return null;
                }
            }
            if (this.isCache()) {
                return NOTE_CACHE.get(note.getNoteId());
            }
            Collection<NoteCollection> noteCollections = this.getNoteCollectionMap().values();
            for (NoteCollection noteCollection : noteCollections) {
                if (noteCollection.getNotes().get(note.getNoteId()) != null) {
                    return noteCollection.getNotes().get(note.getNoteId());
                }
            }
        } else if (noteBase instanceof NoteLabel) {
            NoteLabel noteLabel = (NoteLabel) noteBase;
            if (CommonUtils.isEmpty(noteLabel.getLabelId())) {
                if (!CommonUtils.isEmpty(noteLabel.getLabelName())) {
                    noteLabel.setLabelId(NodeUtils.creatId(noteLabel.getLabelName()));
                } else {
                    return null;
                }
            }
            if (this.isCache()) {
                return NOTE_LABEL_CACHE.get(noteLabel.getLabelId());
            }
            Collection<NoteCollection> noteCollections = this.getNoteCollectionMap().values();
            for (NoteCollection noteCollection : noteCollections) {
                if (noteCollection.getNoteLabels().get(noteLabel.getLabelId()) != null) {
                    return noteCollection.getNoteLabels().get(noteLabel.getLabelId());
                }
            }
        }
        return null;
    }

    /**
     * 删除一条笔记
     * @param note 笔记
     */
    private void consumption(Note note) {
        NoteCollection noteCol = getNoteCollection(note.getCreatedDate());
        logger.info(String.join(" ", "note factory ----- start consumption note:", getParseDate(noteCol.getCreatedDate())));
        noteCol.getNotes().remove(note.getNoteId());
        NoteRelationship noteRelationship = this.getNoteRelationship();
        List<String> labels = noteRelationship.getNoteIdToLabelId().remove(note.getNoteId());
        List<String> delLabels = new ArrayList<>();
        if (!CommonUtils.isEmpty(labels)) {
            for (String labelId : labels) {
                List<String> noteIds = noteRelationship.getLabelIdToNoteId().get(labelId);
                if (!CommonUtils.isEmpty(noteIds)) {
                    noteIds.remove(note.getNoteId());
                    if (noteIds.size() == 0) {
                        delLabels.add(labelId);
                        noteRelationship.getLabelIdToNoteId().remove(labelId);
                    }
                }
            }
        }
        logger.info(String.join(" ", "note factory ----- is synchronization:", String.valueOf(this.isSync())));
        if (this.isSync()) {
            this.saveNoteCollection(noteCol);
            this.saveNoteRelationship();
        }
        if (this.isCache()) {
            NOTE_CACHE.remove(note.getNoteId());
            for (String delLabel : delLabels) {
                NOTE_LABEL_CACHE.remove(delLabel);
            }
        }
        logger.info("note factory ----- consumption note success");
    }

    /**
     * 删除一条标签
     * @param noteLabel 标签
     */
    private void consumption(NoteLabel noteLabel) {
        NoteCollection noteCol = getNoteCollection(noteLabel.getCreatedDate());
        logger.info(String.join(" ", "note factory ----- start consumption noteLabel", getParseDate(noteCol.getCreatedDate())));
        noteCol.getNoteLabels().remove(noteLabel.getLabelId());
        NoteRelationship noteRelationship = this.getNoteRelationship();
        List<String> notes = noteRelationship.getLabelIdToNoteId().remove(noteLabel.getLabelId());
        List<String> delNotes = new ArrayList<>();
        if (!CommonUtils.isEmpty(notes)) {
            for (String noteId : notes) {
                List<String> labelIds = noteRelationship.getNoteIdToLabelId().get(noteId);
                if (!CommonUtils.isEmpty(labelIds)) {
                    labelIds.remove(noteLabel.getLabelId());
                    if (labelIds.size() == 0) {
                        delNotes.add(noteId);
                        noteRelationship.getLabelIdToNoteId().remove(noteId);
                    }
                }
            }
        }
        logger.info(String.join(" ", "note factory ----- is synchronization:", String.valueOf(this.isSync())));
        if (this.isSync()) {
            this.saveNoteCollection(noteCol);
            this.saveNoteRelationship();
        }
        if (this.isCache()) {
            NOTE_LABEL_CACHE.remove(noteLabel.getLabelId());
            for (String delNote : delNotes) {
                NOTE_CACHE.remove(delNote);
            }
        }
        logger.info("note factory ----- consumption noteLabel success");
    }

    /**
     * 生成一条笔记
     */
    private void production(Note note) {
        NoteCollection noteCol = getNoteCollection(note.getCreatedDate());
        logger.info(String.join(" ", "note factory ----- start product note:", getParseDate(noteCol.getCreatedDate())));
        noteCol.getNotes().put(note.getNoteId(), note);
        logger.info(String.join(" ", "note factory ----- is synchronization:", String.valueOf(this.isSync())));
        if (this.isSync()) {
            this.saveNoteCollection(noteCol);
        }
        if (this.isCache()) {
            NOTE_CACHE.put(note.getNoteId(), note);
        }
        logger.info("note factory ----- product note success");
    }

    /**
     * 生成一条标签
     */
    private void production(NoteLabel noteLabel) {
        NoteCollection noteCol = getNoteCollection(noteLabel.getCreatedDate());
        logger.info(String.join(" ", "note factory ----- start product noteLabel", getParseDate(noteCol.getCreatedDate())));
        noteCol.getNoteLabels().put(noteLabel.getLabelId(), noteLabel);
        logger.info(String.join(" ", "note factory ----- is synchronization:", String.valueOf(this.isSync())));
        if (this.isSync()) {
            this.saveNoteCollection(noteCol);
        }
        if (this.isCache()) {
            NOTE_LABEL_CACHE.put(noteLabel.getLabelId(), noteLabel);
        }
        logger.info("note factory ----- product noteLabel success");
    }

    /**
     * 生成多条标签, 同步开启时同步更新文件
     * @param labels 顶层
     */
    public void production(List<NoteLabel> labels) {
        if (labels == null || labels.size() == 0) {
            return;
        }
        NoteCollection noteCol = getNoteCollection(labels.get(0).getCreatedDate());
        logger.info(String.join(" ", "note factory ----- start product noteLabel:", getParseDate(noteCol.getCreatedDate())));
        for (NoteLabel label : labels) {
            if (this.existNote(label)) {
                return;
            }
            if (this.isCache()) {
                NOTE_LABEL_CACHE.put(label.getLabelId(), label);
            }
            noteCol.getNoteLabels().put(label.getLabelId(), label);
        }
        logger.info(String.join(" ", "note factory ----- is synchronization:", String.valueOf(this.isSync())));
        if (this.isSync()) {
            this.saveNoteCollection(noteCol);
        }
        logger.info("note factory ----- product noteLabel success");
    }

    /**
     * 加载所有笔记
     */
    private void loadAllNotes() {
        logger.info("note factory ----- start load all notes");
        List<File> files = FileUtils.listFile(this.getNotePath());
        if (!CommonUtils.isEmpty(files)) {
            for (File file : files) {
                if (file.getName().endsWith(DEFAULT_NOTE_SUF)) {
                    NoteCollection noteCollection = FileUtils.readFile(NoteCollection.class, file);
                    if (noteCollection == null || noteCollection.getCreatedDate() == null) {
                        continue;
                    }
                    getNoteCollectionMap().put(getParseDate(noteCollection.getCreatedDate()), noteCollection);
                    if (this.isCache()) {
                        NOTE_CACHE.putAll(noteCollection.getNotes());
                        NOTE_LABEL_CACHE.putAll(noteCollection.getNoteLabels());
                    }
                    logger.info(String.join(" ", "note factory ----- note info: note path", FileUtils.getCanonicalPath(file)));
                }
            }
        }
        logger.info(String.join(" ",
                "note factory ----- end load all notes", "note quantity is :",
                String.valueOf(getNoteQuantity())),
                "strip", 
                "label quantity is :",
                getLabelQuantity());
    }

    /**
     * 加载笔记映射
     */
    private void loadNoteRelation() {
        logger.info("note factory ----- start load noteRelation");
        List<File> files = FileUtils.listFile(this.getNotePath());
        if (!CommonUtils.isEmpty(files)) {
            for (File file : files) {
                if (file.getName().endsWith(DEFAULT_NOTE_RELATION_NAME)) {
                    NoteRelationship noteRelationship = FileUtils.readFile(NoteRelationship.class, file);
                    if (noteRelationship == null) {
                        throw new IllegalArgumentException("note factory ----- load noteRelation error, note relation file is not found or else");
                    }
                    this.setNoteRelationship(noteRelationship);
                    logger.info(String.join(" ", "note factory ----- noteRelation info: noteRelation path", FileUtils.getCanonicalPath(file)));
                    break;
                }
            }
        }
        logger.info("note factory ----- load noteRelation success");
    }

    /**
     * 保存所有笔记
     */
    public void saveAllNotes () {
        logger.info("note factory ----- start save all notes");
        if (!isNoteEmpty()) {
            for (NoteCollection noteCol : getNoteCollectionMap().values()) {
                this.saveNoteCollection(noteCol);
            }
        }
        logger.info("note factory ----- end save all notes");
    }

    /**
     * 保存一天的笔记
     * @param noteCol 笔记分类
     */
    private void saveNoteCollection(NoteCollection noteCol) {
        try {
            File file = new File(this.getNotePath() + getParseDate(noteCol.getCreatedDate()) + DEFAULT_NOTE_SUF);
            FileUtils.writeFile(noteCol, file);
        } catch (Exception e) {
            throw new IllegalArgumentException("note factory ----- note file write exception", e);
        }
    }

    /**
     * 新增映射关系
     * @param note 笔记
     * @param labels 标签
     */
    public void addNoteRelationship(Note note, List<NoteLabel> labels) {
        if (CommonUtils.isEmpty(labels) || note == null) {
            return;
        }
        for (NoteLabel label : labels) {
            this.creatLabelIdToNoteId(label.getLabelId(), note.getNoteId());
            this.creatNoteIdToLabelId(note.getNoteId(), label.getLabelId());
        }
        if (this.isSync()) {
            this.saveNoteRelationship();
        }
    }

    /**
     * 新增映射关系
     * @param noteId 笔记ID
     * @param labelId 标签ID
     */
    public void addNoteRelationship(String noteId, String labelId) {
        if (CommonUtils.isEmpty(noteId) || CommonUtils.isEmpty(labelId)) {
            return;
        }
        this.creatLabelIdToNoteId(labelId, noteId);
        this.creatNoteIdToLabelId(noteId, labelId);
        if (this.isSync()) {
            this.saveNoteRelationship();
        }
    }

    /**
     * 创建一个标签对应笔记的关系
     * @param labelId 标签ID
     * @param noteId 笔记ID
     */
    private void creatLabelIdToNoteId(String labelId, String noteId) {
        List<String> notes = this.getNoteRelationship().getLabelIdToNoteId().get(labelId);
        if (!CommonUtils.isEmpty(notes)) {
            if (!notes.contains(noteId)) {
                notes.add(noteId);
            }
        } else {
            notes = new ArrayList<>();
            notes.add(noteId);
            this.getNoteRelationship().getLabelIdToNoteId().put(labelId, notes);
        }
    }

    /**
     * 创建一个笔记对应标签的关系
     * @param noteId 笔记ID
     * @param labelId 标签ID
     */
    private void creatNoteIdToLabelId(String noteId, String labelId) {
        List<String> labels = this.getNoteRelationship().getNoteIdToLabelId().get(noteId);
        if (!CommonUtils.isEmpty(labels)) {
            if (!labels.contains(labelId)) {
                labels.add(labelId);
            }
        } else {
            labels = new ArrayList<>();
            labels.add(labelId);
            this.getNoteRelationship().getNoteIdToLabelId().put(noteId, labels);
        }
    }

    /**
     * 保存笔记映射
     */
    public void saveNoteRelationship() {
        try {
            File file = new File(this.getNotePath() + DEFAULT_NOTE_RELATION_NAME);
            FileUtils.writeFile(this.getNoteRelationship(), file);
        } catch (Exception e) {
            throw new IllegalArgumentException("note factory ----- note relation file write exception", e);
        }
    }

    /**
     * 查找某一天的笔记
     * @param date 日期
     * @return <code>NoteCollection</cede>
     */
    private NoteCollection getNoteCollection(LocalDateTime date) {
        String noteDate = date == null ? getParseDate(LocalDateTime.now()) : getParseDate(date);
        NoteCollection noteCol = getNoteCollectionMap().get(noteDate);
        if (noteCol == null) {
            date = LocalDateTime.now();
            noteCol = new NoteCollection(date, date, new HashMap<>(), new HashMap<>());
            getNoteCollectionMap().put(noteDate, noteCol);
        }
        return noteCol;
    }

    /**
     * 获取所有笔记
     * @return <code>List</code>
     */
    public List<Note> listNote() {
        if (this.isCache()) {
            return new ArrayList<>(NOTE_CACHE.values());
        }
        if (isNoteEmpty()) {
            return new ArrayList<>();
        }
        List<Note> notes = new ArrayList<>();
        for (NoteCollection noteCol : getNoteCollectionMap().values()) {
            notes.addAll(noteCol.getNotes().values());
        }
        logger.info(String.join(" ", "note factory ----- number of notes found is", String.valueOf(notes.size())));
        return notes;
    }

    /**
     * 获取指定笔记
     * @param noteIds 笔记ID
     * @return <code>List</code>
     */
    public List<Note> listNote(List<String> noteIds) {
        if (isNoteEmpty() || CommonUtils.isEmpty(noteIds)) {
            return new ArrayList<>();
        }
        List<Note> notes = new ArrayList<>();
        for (String noteId : noteIds) {
            if (this.isCache()) {
                notes.add(NOTE_CACHE.get(noteId));
            } else {
                for (NoteCollection noteCol : getNoteCollectionMap().values()) {
                    Note note = noteCol.getNotes().get(noteId);
                    if (note != null) {
                        notes.add(note);
                        break;
                    }
                }
            }
        }
        return notes;
    }

    /**
     * 获取所有标签
     * @return <code>List</code>
     */
    public List<NoteLabel> listNoteLabel() {
        if (this.isCache()) {
            return new ArrayList<>(NOTE_LABEL_CACHE.values());
        }
        if (isNoteEmpty()) {
            return new ArrayList<>();
        }
        List<NoteLabel> noteLabels = new ArrayList<>();
        for (NoteCollection noteCol : getNoteCollectionMap().values()) {
            if (!CommonUtils.isEmpty(noteCol.getNoteLabels())) {
                noteLabels.addAll(noteCol.getNoteLabels().values());
            }
        }
        logger.info(String.join(" ", "note factory ----- number of noteLabels found is", String.valueOf(noteLabels.size())));
        return noteLabels;
    }

    /**
     * 笔记路径校验
     */
    private void verificationNotePath() {
        String notePath = this.getNotePath();
        if (CommonUtils.isEmpty(notePath)) {
            notePath = FileUtils.getCanonicalPath(DEFAULT_NOTE_PATH) + DEFAULT_NOTE_PATH;
            this.setNotePath(notePath);
            return;
        } else if (!notePath.endsWith("\\")) {
            notePath = notePath + "\\";
        }
        if (!FileUtils.isExist(notePath)) {
            throw new IllegalArgumentException("note factory ----- note path is not exist");
        }
        this.setNotePath(notePath);
    }

    /**
     * 获取笔记数量
     * @return <code>int</code>
     */
    public int getNoteQuantity () {
        int quantity = 0;
        if (!isNoteEmpty()) {
            for (NoteCollection noteCollection : getNoteCollectionMap().values()) {
                quantity = quantity + noteCollection.getNotes().size();
            }
        }
        return quantity;
    }

    /**
     * 获取标签数量
     * @return <code>int</code>
     */
    public int getLabelQuantity () {
        int quantity = 0;
        if (!isNoteEmpty()) {
            for (NoteCollection noteCollection : getNoteCollectionMap().values()) {
                quantity = quantity + noteCollection.getNoteLabels().size();
            }
        }
        return quantity;
    }

    /**
     * use cache
     * 查询笔记对应的标签
     * @param noteId 笔记 ID
     * @return 标签 list
     */
    public List<NoteLabel> listNoteLabelByNoteId (String noteId) {
        List<NoteLabel> noteLabels = new ArrayList<>();
        NoteRelationship noteRelationship = this.getNoteRelationship();
        List<String> labelIds = noteRelationship.getNoteIdToLabelId().get(noteId);
        if (CommonUtils.isEmpty(labelIds)) {
            return new ArrayList<>();
        }
        if (this.isCache()) {
            for (String labelId : labelIds) {
                NoteLabel noteLabel = NOTE_LABEL_CACHE.get(labelId);
                if (noteLabel != null) {
                    noteLabels.add(noteLabel);
                }
            }
        } else {
            for (NoteCollection noteCol : getNoteCollectionMap().values()) {
                for (String labelId : labelIds) {
                    if (!CommonUtils.isEmpty(noteCol.getNoteLabels())) {
                        NoteLabel noteLabel = noteCol.getNoteLabels().get(labelId);
                        if (noteLabel != null) {
                            noteLabels.add(noteLabel);
                        }
                    }
                }
            }
        }
        return noteLabels;
    }

    /**
     * use cache
     * 查询标签对应的笔记
     * @param noteLabelId 标签 ID
     * @return 笔记 list
     */
    public List<Note> listNoteByNoteLabelId (String noteLabelId) {
        List<Note> notes = new ArrayList<>();
        NoteRelationship noteRelationship = this.getNoteRelationship();
        List<String> noteIds = noteRelationship.getLabelIdToNoteId().get(noteLabelId);
        if (CommonUtils.isEmpty(noteIds)) {
            return new ArrayList<>();
        }
        if (this.isCache()) {
            for (String nodeId : noteIds) {
                Note note = NOTE_CACHE.get(nodeId);
                if (note != null) {
                    notes.add(note);
                }
            }
        } else {
            for (NoteCollection noteCol : getNoteCollectionMap().values()) {
                for (String nodeId : noteIds) {
                    if (!CommonUtils.isEmpty(noteCol.getNotes())) {
                        Note note = noteCol.getNotes().get(nodeId);
                        if (note != null) {
                            notes.add(note);
                        }
                    }
                }
            }
        }
        return notes;
    }

    public boolean isNoteEmpty () {
        return CommonUtils.isEmpty(getNoteCollectionMap());
    }

    private String getParseDate(LocalDateTime date) {
        return date.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    }

    public String getNotePath() {
        return notePath;
    }

    public void setNotePath(String notePath) {
        this.notePath = notePath;
    }

    public boolean isSync() {
        return isSync;
    }

    public void setSync(boolean sync) {
        isSync = sync;
        if (sync) {
            this.saveAllNotes();
            this.saveNoteRelationship();
        }
        logger.info(String.join(" ", "note factory ----- sync switch", sync ? "open" : "close"));
    }

    public NoteRelationship getNoteRelationship() {
        return noteRelationship;
    }

    public void setNoteRelationship(NoteRelationship noteRelationship) {
        this.noteRelationship = noteRelationship;
    }

    public Map<String, NoteCollection> getNoteCollectionMap() {
        return noteCollectionMap;
    }

    public void setNoteCollectionMap(Map<String, NoteCollection> noteCollectionMap) {
        this.noteCollectionMap = noteCollectionMap;
    }

    public boolean isCache() {
        return isCache;
    }

    public void setCache(boolean cache) {
        isCache = cache;
        if (cache) {
            this.flushCache();
        } else {
            NOTE_CACHE.clear();
            NOTE_LABEL_CACHE.clear();
        }
        logger.info(String.join(" ", "note factory ----- cache switch", cache ? "open" : "close"));
    }
}
