package com.micro.jfxexe.handler.chain;

import com.micro.common.anno.ChainOrder;
import com.micro.common.util.chain.ChainHandler;
import com.micro.common.util.chain.ChainParam;
import com.micro.common.util.other.CommonUtils;
import com.micro.jfxexe.domain.Note;
import com.micro.jfxexe.domain.SearchParam;
import com.micro.jfxexe.factory.IStaticFactorySupport;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author XiongJiaMin
 * @apiNote 标签搜索
 * @since 2023-01-04 14:48
 **/
@ChainOrder(name = "searchNoteChain")
@SuppressWarnings("unused")
public class LabelSearchChain extends ChainHandler implements IStaticFactorySupport {

    @Override
    public void handler(ChainParam chainParam) {
        SearchParam searchParam = (SearchParam) chainParam;
        if (CommonUtils.isEmpty(searchParam.getLabels())) {
            return;
        }
        List<Note> notes;
        List<List<String>> nodeIdLists = searchParam.getLabels().stream().map(labelF -> {
            logger.info(String.join(" ", "note search ----- label name:", labelF.getLabelName()));
            return noteFactory.getNoteRelationship().getLabelIdToNoteId().get(labelF.getLabelId());
        }).collect(Collectors.toList());
        if (nodeIdLists.size() > 1) {
            notes = nodeIdLists.stream()
                    .reduce((l1, l2) -> {
                        l1.retainAll(l2);
                        return l1;
                    }).map(noteFactory::listNote).orElse(Collections.emptyList());
        } else {
            notes = noteFactory.listNote(nodeIdLists.get(0));
        }
        logger.info(String.join(" ", "note search ----- label matches:", (!CommonUtils.isEmpty(notes) ? "have" : "not have")));
        searchParam.setNotes(notes);
    }
}
