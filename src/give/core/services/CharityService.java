/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package give.core.services;

import give.base.model.BaseModel;
import give.base.services.BaseService;
import give.core.model.CategoryModel;
import give.core.model.CharityModel;

import java.io.File;
import java.util.ArrayList;

import static give.base.controller.BaseController.getAppPath;

/**
 *
 * @author Administrator
 */
public class CharityService extends BaseService {
    
    public CharityService() {
        super();
    }

    public ArrayList<CharityModel> getAllCharities() {
        String strSQL = "SELECT charity.*, category.name categoryName FROM charity LEFT JOIN category ON charity.`category`=category.`id`";

        ArrayList<BaseModel> arr = this.m_dbHelper.executeSQLAndReturnArray(strSQL, new Object[]{}, CharityModel.class.getSimpleName());

        ArrayList<CharityModel> arrReturn = new ArrayList<>();
        for(int i = 0; i < arr.size(); i++) {
            arrReturn.add((CharityModel)arr.get(i));
        }

        return arrReturn;
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
            builder.append(charities.get(i).getID());
        }
        
        String strSQL = "DELETE FROM charity WHERE id IN(" + builder.toString() + ")";
        this.m_dbHelper.executeSQL(strSQL, null);

        for(int i = 0; i < charities.size(); i++) {
            String imageUrl = getAppPath() + "images/charity/" + charities.get(i).getImageUrl();
            File file = new File(imageUrl);
            if(file.exists()) {
                file.delete();
            }
        }
    }

    public ArrayList<CategoryModel> getCategories() {
        String strSQL = "SELECT * FROM category";

        ArrayList<BaseModel> arr = this.m_dbHelper.executeSQLAndReturnArray(strSQL, new Object[]{}, CategoryModel.class.getSimpleName());

        ArrayList<CategoryModel> arrReturn = new ArrayList<>();
        for(int i = 0; i < arr.size(); i++) {
            arrReturn.add((CategoryModel)arr.get(i));
        }

        return arrReturn;
    }
}
