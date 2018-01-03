/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package urs.areas;

import java.util.ArrayList;
import java.util.List;
import urs.observerinterfaces.IObserver;
import urs.observerinterfaces.ISubject;
import urs.observerinterfaces.SubjectImplementation;
import urs.states.NormalState;
import urs.states.States;

/**
 *
 * @author tobybessant
 */
public class Campus implements IAreaState, ISubject, IObserver {
    private String _campusName;
    private States _campusState;
    
    private ArrayList<Building> _buildingList;
    private final ISubject subject = new SubjectImplementation();
    
    public Campus(String name) {
        this._campusName = name;
        this._campusState = new NormalState();
        this._buildingList = new ArrayList<>();
    }
    
    public String getCampusName() {
        return this._campusName;
    }

    public void setCampusName(String _campusName) {
        this._campusName = _campusName;
    }
    
    public Boolean addBuilding(Building b){
        Boolean result = false;
        if (null != b)
        {
            if (!this._buildingList.contains(b))
            {
                result = this._buildingList.add(b);
                if (result)
                {
                    b.registerObserver(this);
                    this.notifyObservers();
                }
            }
        }
        return result;
    }
    
    public Boolean removeBuilding(Building b) {
        Boolean result = false;
        if (null != b)
        {
            if (null != _buildingList && this._buildingList.size() > 0)
            {
                result = this._buildingList.remove(b);
                if(result){
                    b.removeObserver(this);
                    this.notifyObservers();
                }
            }
        }
        return result;
    }
    
    public ArrayList<Building> getBuildingList(){
        
        ArrayList<Building> result = new ArrayList<>();
        for (Building b : this._buildingList)
        {
            result.add(b);
        }
        return result;
    }
    
    @Override
    public Boolean setState(States s) {
        Boolean result = false;
        if(s != null) {
            for(int i = 0; i < this._buildingList.size(); i++) {
                this._buildingList.get(i).setState(s);
            }
            this._campusState = s;
            result = true;
        }
        return result;
    }
    
    @Override
    public States getState() {
        return this._campusState;
    }

    @Override
    public Boolean registerObserver(IObserver o) {
        Boolean result = this.subject.registerObserver(o);
        return result;
    }

    @Override
    public Boolean removeObserver(IObserver o) {
        Boolean result = this.subject.removeObserver(o);
        return result;
    }

    @Override
    public void notifyObservers() {
        this.subject.notifyObservers();
    }

    @Override
    public void Update() {
        this.notifyObservers();
    }
    
    
    
}
