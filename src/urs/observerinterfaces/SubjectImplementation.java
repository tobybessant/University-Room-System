/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package urs.observerinterfaces;

import java.util.ArrayList;
import urs.areas.Campus;
import urs.areas.Room;
import urs.cards.Card;

/**
 *
 * @author Toby
 */
public class SubjectImplementation implements ISubject {
    
    private ArrayList<IObserver> _observerList = null;
    
    @Override
    public Boolean registerObserver(IObserver o) {
        Boolean result = false;
        
        if (o != null)
        {
            if (this._observerList == null)
            {
                this._observerList = new ArrayList<>();
            }
            if (!this._observerList.contains(o))
            {
                result = this._observerList.add(o);
            }
        }
        return result;
    }

    @Override
    public Boolean removeObserver(IObserver o) {
        Boolean result = false;
        if (o != null)
        {
            if (this._observerList != null && this._observerList.size() > 0)
            {
                result = this._observerList.remove(o);
            }
        }
        return result;
    }

    @Override
    public void notifyObservers() {
        if (this._observerList != null && this._observerList.size() > 0)
        {
            for (IObserver currentObserver : this._observerList)
            {
                currentObserver.Update();
            }
        }
    }
    
}
