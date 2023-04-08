package com.micro.jfxexe.handler.chain;

import com.micro.common.anno.ChainOrder;
import com.micro.common.util.chain.ChainHandler;
import com.micro.common.util.chain.ChainParam;
import com.micro.common.util.other.CommonUtils;
import com.micro.common.util.other.KMPUtil;
import com.micro.jfxexe.domain.LabelF;
import com.micro.jfxexe.domain.Note;
import com.micro.jfxexe.domain.SearchParam;
import com.micro.jfxexe.factory.IStaticFactorySupport;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author XiongJiaMin
 * @apiNote 关键字搜索
 * @since 2023-01-04 14:50
 **/
@ChainOrder(order = 2, name = "searchNoteChain")
@SuppressWarnings("unused")
public class TextSearchChain extends ChainHandler implements IStaticFactorySupport {
    
    @Override
    public void handler(ChainParam chainParam) {
        SearchParam searchParam = (SearchParam) chainParam;
        String text = searchParam.getSearchText();
        if (CommonUtils.isEmpty(text)) {
            return;
        }
        List<LabelF> labels = searchParam.getLabels();
        List<Note> notes = searchParam.getNotes();
        notes = CommonUtils.isEmpty(labels) && CommonUtils.isEmpty(notes) ? noteFactory.listNote() : notes;
        logger.info(String.join(" ", "note search ----- search text:", text));
        notes = notes.stream()
                .filter(note -> KMPUtil.kmpMapping(note.getNoteText(), text) != -1)
                .collect(Collectors.toList());
        searchParam.setNotes(notes);
    }
}
