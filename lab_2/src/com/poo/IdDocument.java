package com.poo;
/**
 * In this class we have the Document Identification constructor of our program
 *
 * @author Bruno Teodoro
 * @author David Arco
 * @author Diego Soares
 * @version 1.0
 * @since 08/11/2020
 */

public class IdDocument {

    private String number;
    private String type;

    /**
     * This is the constructor for the IdDocument
     * We have the following parameters
     * @param number (document identification number)
     * @param type (Type of document - BI/CC or Passport)
     */
    public IdDocument(String number, String type){
        this.number = number;
        this.type = type;
    }

    /**
     * Method to return Identification number
     * @return number (Identification number)
     */
    public Object getNumber(){
        return this.number;
    }

    /**
     * Method to return Type of document
     * @return type (Type of Document)
     */
    public Object getType(){
        return this.type;
    }

    /**
     * Boolean method used to verify if document Type and number matches current Documentation information
     * @param document (Object document for verification)
     * @return True or False
     */
    public boolean equals(IdDocument document) {
        return (this.number.equals(document.getNumber()) && this.type.equals(document.getType()));
    }

}
