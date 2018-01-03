/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package urs.areas;

import java.util.ArrayList;
import urs.observerinterfaces.IObserver;
import urs.observerinterfaces.ISubject;
import urs.observerinterfaces.SubjectImplementation;
import urs.states.NormalState;
import urs.states.States;

/**
 *
 * @author tobybessant
 */
public class Building implements IAreaState, ISubject, IObserver {
    private String _buildingName;
    private String _buildingCode;
    private States _buildingState;
    
    private ArrayList<Floor> _floorList;
    private final ISubject _subject = new SubjectImplementation();
    
    public Building(String name, String code) {
        this._buildingName = name;
        this._buildingCode = code;
        this._floorList = new ArrayList<>();
        this._buildingState = new NormalState();        
    }
    
    public String getBuildingName() {
        return this._buildingName;
    }

    public void setBuildingName(String _buildingName) {
        this._buildingName = _buildingName;
    }

    public String getBuildingCode() {
        return this._buildingCode;
    }

    public void setBuildingCode(String _buildingCode) {
        this._buildingCode = _buildingCode;
    }
    
    public Boolean addFloor(Floor f){
        Boolean result = false;
        if (null != f)
        {
            if (!this._floorList.contains(f))
            {
                result = this._floorList.add(f);
                if (result)
                {
                    f.registerObserver(this);
                    this.notifyObservers();
                }
            }
        }
        return result;
    }
    
    public Boolean removeFloor(Floor f) {
        Boolean result = false;
        if (null != f)
        {
            if (null != _floorList && this._floorList.size() > 0)
            {
                result = this._floorList.remove(f);
                if(result){
                    f.removeObserver(this);
                    this.notifyObservers();
                }
            }
        }
        return result;
    }
    
    @Override
    public Boolean setState(States s) {
        Boolean result = false;
        if(s != null) {
            for(int i = 0; i < this._floorList.size(); i++) {
                this._floorList.get(i).setState(s);
            }
            this._buildingState = s;
            result = true;
        }
        return result;
    }

    @Override
    public States getState() {
        return this._buildingState;
    }

    @Override
    public Boolean registerObserver(IObserver o) {
        Boolean result =this._subject.registerObserver(o);
        return result;
    }

    @Override
    public Boolean removeObserver(IObserver o) {
        Boolean result = this._subject.removeObserver(o);
        return result;
    }

    @Override
    public void notifyObservers() {
        this._subject.notifyObservers();
    }

    @Override
    public void Update() {
        this.notifyObservers();
    }
}
