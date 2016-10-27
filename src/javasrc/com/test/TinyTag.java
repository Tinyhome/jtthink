package com.test;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * Created by Tinyhome on 16/7/8.
 */
public class TinyTag extends TagSupport {

    @Override
    public int doEndTag() throws JspException {
        JspWriter jw =pageContext.getOut();
        try {
            jw.write("Tinyhome's taglib");
            jw.write(pageContext.getRequest().getAttribute("name").toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return EVAL_PAGE;
    }
}
