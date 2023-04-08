package com.micro.jfxexe.handler.chain;

import com.micro.common.anno.ChainOrder;
import com.micro.common.util.chain.ChainHandler;
import com.micro.common.util.chain.ChainParam;
import com.micro.common.util.other.CommonUtils;
import com.micro.jfxexe.common.NodeUtils;
import com.micro.jfxexe.domain.LabelF;
import com.micro.jfxexe.domain.NoteLabel;
import com.micro.jfxexe.domain.SearchParam;
import com.micro.jfxexe.factory.IStaticFactorySupport;

import java.util.List;

/**
 * @author XiongJiaMin
 * @apiNote 计数
 * @since 2023-01-06 16:39
 **/
@ChainOrder(order = 5, name = "searchNoteChain")
@SuppressWarnings("unused")
public class CountSearchChain extends ChainHandler implements IStaticFactorySupport {

    @Override
    public void handler(ChainParam chainParam) {
        SearchParam searchParam = (SearchParam) chainParam;
        if (!searchParam.getEvent().isActualTime()) {
            if (!CommonUtils.isEmpty(searchParam.getSearchText())) {
                addLabelTime(NodeUtils.creatId(searchParam.getSearchText()), searchParam.getSearchText(), true);
            }
            if (!CommonUtils.isEmpty(searchParam.getLabels())) {
                List<LabelF> labels = searchParam.getLabels();
                for (LabelF labelF : labels) {
                    addLabelTime(labelF.getLabelId(), labelF.getLabelName(), false);
                }
            }
        }
    }

    private void addLabelTime (String labelId, String labelName, boolean isSearch) {
        NoteLabel noteLabel = noteFactory.getNoteLabelById(labelId);
        if (noteLabel == null) {
            noteLabel = new NoteLabel();
            noteLabel.setLabelId(labelId);
            noteLabel.setLabelName(labelName);
        }
        noteLabel.setSearchText(isSearch);
        noteLabel.addTime();
        noteFactory.updateNote(noteLabel);
    }
}
