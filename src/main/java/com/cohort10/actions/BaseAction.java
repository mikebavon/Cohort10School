package com.cohort10.actions;

import com.cohort10.util.MyDateConverter;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.PropertyUtilsBean;

import javax.servlet.http.HttpServlet;
import java.util.Date;

public abstract class BaseAction extends HttpServlet {

    public BeanUtilsBean formPopulator() {
        ConvertUtilsBean convertUtilsBean = new ConvertUtilsBean();
        convertUtilsBean.register(new MyDateConverter(), Date.class);

        return new BeanUtilsBean(convertUtilsBean, new PropertyUtilsBean());
    }


}
