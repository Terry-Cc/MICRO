package com.micro.jfxexe.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author XiongJiaMin
 * @apiNote 分词器
 * @since 2022-12-27 13:28
 **/
public class IKAnalyzerUtil {

    protected static Logger logger = LoggerFactory.getLogger(IKAnalyzerUtil.class);

    public static List<String> cut(String msg) {
        List<String> list = new ArrayList<>();
        try {
            StringReader sr = new StringReader(msg);
            IKSegmenter ik = new IKSegmenter(sr, true);
            Lexeme lex;
            while ((lex = ik.next()) != null) {
                list.add(lex.getLexemeText());
            }
        } catch (IOException e) {
            logger.error("IKAnalyzerUtil error:", e);
        }
        return list;
    }
}
