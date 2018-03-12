/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package give.core.model;

import give.base.model.BaseModel;
import static give.core.model.UserModel.USER_TYPE_COMMON;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class CharityModel extends BaseModel {
    
    protected String m_strName;
    protected String m_strSite;
    protected String m_strCategory;
    protected String m_strBIO;
    protected String m_strMetric;
    protected Double m_dblAmount;
    protected Integer m_nNumber;
    protected String m_strImage;
    
    public CharityModel() {
        super();
    }
    
    public CharityModel(ResultSet rs) {
        super(rs);
        
        this.m_strName      = (String)this.getValueFrom(rs, "name", "");
        this.m_strSite      = (String)this.getValueFrom(rs, "website", "");
        this.m_strCategory  = (String)this.getValueFrom(rs, "category", "");
        this.m_strBIO       = (String)this.getValueFrom(rs, "password", "");
        this.m_strMetric    = (String)this.getValueFrom(rs, "metric", "");
        this.m_strImage     = (String)this.getValueFrom(rs, "logo", "");
        this.m_dblAmount    = (Double)this.getValueFrom(rs, "amount", USER_TYPE_COMMON);
        this.m_nNumber      = (Integer)this.getValueFrom(rs, "quantity", USER_TYPE_COMMON);
    }
    
    public String getName() { return m_strName; }
    public void setName(String strValue) { this.m_strName = strValue; }
    
    public String getSite() { return m_strSite; }
    public void setSite(String strValue) { this.m_strSite = strValue; }
    
    public String getCategory() { return m_strCategory; }
    public void setCategory(String strValue) { this.m_strCategory = strValue; }
    
    public String getBIO() { return m_strBIO; }
    public void setBIO(String strValue) { this.m_strBIO = strValue; }
    
    public String getImage() { return m_strImage; }
    public void setImage(String strValue) { this.m_strImage = strValue; }
    
    public String getMetric() { return m_strMetric; }
    public void setMetric(String strValue) { this.m_strMetric = strValue; }
    
    public Double getAmount() { return m_dblAmount; }
    public void setAmount(Double dblValue) { this.m_dblAmount = dblValue; }
    
    public Integer getNumber() { return m_nNumber; }
    public void setNumber(Integer nNumber) { this.m_nNumber = nNumber; }

    @Override
    public ArrayList<Object> toArrayForInsert() {
        ArrayList<Object> arr = new ArrayList<>();
        
        arr.add(m_strName);
        arr.add(m_strImage);
        arr.add(m_strBIO);
        arr.add(m_strSite);
        arr.add(m_strCategory);        
        arr.add(m_strMetric);
        arr.add(m_dblAmount);
        arr.add(m_nNumber);
        
        return arr;
    }

    @Override
    public ArrayList<Object> toArrayForUpdate() {
        ArrayList<Object> arr = new ArrayList<>();
        
        arr.add(m_strName);
        arr.add(m_strImage);
        arr.add(m_strBIO);
        arr.add(m_strSite);
        arr.add(m_strCategory);        
        arr.add(m_strMetric);
        arr.add(m_dblAmount);
        arr.add(m_nNumber);
        
        arr.add(m_nID);
        
        return arr;
    }
    
}
