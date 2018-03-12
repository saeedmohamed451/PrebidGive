/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package give.core.services;

import give.base.services.BaseService;
import give.core.model.CharityModel;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class CharityService extends BaseService {
    
    public CharityService() {
        super();
    }
    
    public boolean addNewCharity(CharityModel newCharity) {
        String strSQL = "INSERT INTO charity(name, logo, bio, website, category, metric, amount, quantity) VALUES(?,?,?,?,?,?,?,?)";
        
        long insertedID = this.m_dbHelper.insertRowByPreparedStatement(strSQL, newCharity.toArrayForInsert());
        
        return insertedID > -1;
    }
    
    public boolean updateCharity(CharityModel newCharity) {
        String strSQL = "UPDATE charity SET name=?, logo=?, bio=?, website=?, category=?, metric=?, amount=?, quantity=? WHERE id=?";
        
        return this.m_dbHelper.updateRowByPreparedStatement(strSQL, newCharity.toArrayForUpdate());
    }
    
    public void deleteCharities(ArrayList<CharityModel> charities) {
        if(charities == null || charities.isEmpty()) return;
        
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < charities.size(); i++) {
            builder.append(i > 0 ? "," : "");
            builder.append(charities.get(i));
        }
        
        String strSQL = "DELETE charity WHERE id IN(" + builder.toString() + ")";
        this.m_dbHelper.executeSQL(strSQL, null);
    }
}
