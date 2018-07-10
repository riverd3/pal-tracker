package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {
    private  Map<Long, TimeEntry> timeEntryMap = new HashMap<>();

    public InMemoryTimeEntryRepository(){
        timeEntryMap = new HashMap<>();
    }

    @Override
    public TimeEntry create(TimeEntry any) {
        any.setId(timeEntryMap.size()+1);
        timeEntryMap.put(any.getId(), any);
        return any;
    }

    @Override
    public TimeEntry delete(long l) {
        TimeEntry timeEntry = timeEntryMap.get(l);
        timeEntryMap.remove(l);
        return timeEntry;
    }

    @Override
    public TimeEntry update(long id, TimeEntry any) {
        TimeEntry oldTimeEntry = timeEntryMap.get(id);
        if(oldTimeEntry==null){
            return null;
        }else {
            any.setId(id);
            timeEntryMap.put(id, any);
            return any;
        }
    }

    @Override
    public TimeEntry find(long l) {

        return timeEntryMap.get(l);
    }

    @Override
    public List<TimeEntry> list() {
        return new ArrayList(timeEntryMap.values());
    }
}
