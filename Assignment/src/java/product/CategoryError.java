/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package product;

/**
 *
 * @author markhipz
 */
public class CategoryError {

    private String categoryIDError;
    private String categoryNameError;
    private String unknownError;

    public CategoryError() {
        this.categoryIDError ="";
        this.categoryNameError = "";
        this.unknownError = "";
    }

    public CategoryError(String categoryIDError, String categoryNameError, String unknownError) {
        this.categoryIDError = categoryIDError;
        this.categoryNameError = categoryNameError;
        this.unknownError = unknownError;
    }

    public String getCategoryIDError() {
        return categoryIDError;
    }

    public void setCategoryIDError(String categoryIDError) {
        this.categoryIDError = categoryIDError;
    }

    public String getCategoryNameError() {
        return categoryNameError;
    }

    public void setCategoryNameError(String categoryNameError) {
        this.categoryNameError = categoryNameError;
    }

    public String getUnknownError() {
        return unknownError;
    }

    public void setUnknownError(String unknownError) {
        this.unknownError = unknownError;
    }

}
