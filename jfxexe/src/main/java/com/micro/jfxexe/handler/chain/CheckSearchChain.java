package com.micro.jfxexe.handler.chain;

import com.micro.common.anno.ChainOrder;
import com.micro.common.util.chain.ChainHandler;
import com.micro.common.util.chain.ChainParam;
import com.micro.common.util.other.CommonUtils;
import com.micro.jfxexe.domain.LabelF;
import com.micro.jfxexe.domain.Note;
import com.micro.jfxexe.domain.SearchParam;
import com.micro.jfxexe.factory.IStaticFactorySupport;

import java.util.List;

/**
 * @author XiongJiaMin
 * @apiNote 校验处理
 * @since 2023-01-04 17:03
 **/
@ChainOrder(order = 3, name = "searchNoteChain")
@SuppressWarnings("unused")
public class CheckSearchChain extends ChainHandler implements IStaticFactorySupport {

    @Override
    public void handler(ChainParam chainParam) {
        SearchParam searchParam = (SearchParam) chainParam;
        List<Note> notes = searchParam.getNotes();
        List<LabelF> labels = searchParam.getLabels();
        String text = searchParam.getSearchText();
        if (CommonUtils.isEmpty(notes)) {
            if (!CommonUtils.isEmpty(labels) || !CommonUtils.isEmpty(text)) {
                searchParam.setNone(true);
            }
            notes = noteFactory.listNote();
        }
        searchParam.setNotes(notes);
    }
}
