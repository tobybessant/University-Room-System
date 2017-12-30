/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universityroomsystem.areas;

import java.util.ArrayList;
import java.util.List;
import universityroomsystem.states.States.State;

/**
 *
 * @author tobybessant
 */
public class Campus implements IAreaState {
    private String _campusName;
    private ArrayList<Building> _buildingList;
    private State _campusState;

    public Campus(String name) {
        this._campusName = name;
        if(this._buildingList == null){
            this._buildingList = new ArrayList<Building>();
        }
    }
    
    public String getCampusName() {
        return this._campusName;
    }

    public void setCampusName(String _campusName) {
        this._campusName = _campusName;
    }
    
    public Boolean addBuilding(Building b){
        Boolean result = false;
        
        if(this._buildingList != null && !this._buildingList.contains(b)){
            this._buildingList.add(b);
            result = true;
        } else {
            this._buildingList = new ArrayList<Building>();
            this._buildingList.add(b);
            result = true;
        }
        return result;
    }
    public Boolean removeBuilding(Building b) {
        Boolean result = false;
        if(b != null && this._buildingList.contains(b)){
            this._buildingList.remove(b);
            result = true;
        }
        return result;
    }
    
    @Override
    public Boolean setState(State s) {
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
    public String getDetails() {
        return this._campusName;
    }
    
    
}
