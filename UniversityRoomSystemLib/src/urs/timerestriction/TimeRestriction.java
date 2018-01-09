/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package urs.timerestriction;

import java.time.LocalTime;

/**
 *
 * @author apple
 */
public class TimeRestriction {
    
    private Boolean _timeRestricted = true;
    private LocalTime _startTime;
    private LocalTime _endTime;

    public Boolean getTimeRestricted() {
        return _timeRestricted;
    }

    public LocalTime getStartTime() {
        return _startTime;
    }

    public void setStartTime(LocalTime _startTime) {
        this._startTime = _startTime;
        this._timeRestricted = true;
    }

    public LocalTime getEndTime() {
        return _endTime;
    }

    public Boolean setEndTime(LocalTime _endTime) {
        Boolean result = false;
        
        this._endTime = _endTime;
        this._timeRestricted = true;
        
        return result;
    }
    public Boolean removeTimeRestriction() {
        Boolean result = false;
        return result = (this._timeRestricted = false);
    }
    
}
