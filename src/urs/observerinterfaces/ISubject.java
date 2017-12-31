/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package urs.observerinterfaces;

/**
 *
 * @author tobybessant
 */
public interface ISubject {
    Boolean registerObserver();
    Boolean removeObserver();
    void notifyObservers();
}
