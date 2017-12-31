/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package urs.areas;

import java.util.ArrayList;
import urs.states.NormalState;
import urs.states.States;

/**
 *
 * @author tobybessant
 */
public class Building implements IAreaState {
    private String _buildingName;
    private String _buildingCode;
    private ArrayList<Floor> _floorList;
    private States _buildingState;
    
    public Building(String name, String code) {
        this._buildingName = name;
        this._buildingCode = code;
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
        if(this._floorList != null && !this._floorList.contains(f)){
            this._floorList.add(f);
            result = true;
        } else {
            this._floorList = new ArrayList<Floor>();
            this._floorList.add(f);
            result = true;
        }
        return result;
    }
    
    public Boolean removeFloor(Floor f) {
        Boolean result = false;
        if(f != null && this._floorList.contains(f)){
            this._floorList.remove(f);
            result = true;
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
    public String getDetails() {
        return this._buildingName;
    }

    @Override
    public States getState() {
        return this._buildingState;
    }
}
