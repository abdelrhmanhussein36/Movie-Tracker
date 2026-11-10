package com.task;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JsonUtils <T extends watchable> {
    
    
        private final File file;
        private final ObjectMapper Mapper;
        private ArrayList<T> itemslist;
        public JsonUtils(String filepath, Class<T> itemClass) {
            this.file = new File(filepath);
            this.Mapper = new ObjectMapper().registerModule(new JavaTimeModule()).disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            this.itemslist = new ArrayList<>();
                try {
                    if (file.exists() && file.length() > 0) {
                        com.fasterxml.jackson.databind.JavaType itemslistType = Mapper.getTypeFactory().constructCollectionType(ArrayList.class, itemClass);
                        this.itemslist = Mapper.readValue(file, itemslistType);
                    }
                } catch (IOException e) {
                    System.out.println("Could not load existing items from file: " + e.getMessage());
                }
                    }

        public void additem(T item) {
            itemslist.add(item);
            try{
                Mapper.writeValue(file, itemslist);
                System.out.println("Item has been written to the file.");
            } catch (Exception e) {
                System.out.println("An error occurred while writing the item to the file: " + e.getMessage());
            }

    }

    public void removeitem(T itemname) {
        Iterator<T> iterator = itemslist.iterator();
        while (iterator.hasNext()) {
            T currentItem = iterator.next();
            boolean isremoved = false;
            if (currentItem.equals(itemname) && !isremoved) {
                iterator.remove();
                isremoved = true;
                try {
                    Mapper.writeValue(file, itemslist);
                    System.out.println("Item has been removed from the file.");
                } catch (Exception e) {
                    System.out.println("An error occurred while removing the item from the file: " + e.getMessage());
                }
                return;
            }
        }
    }

    public T searchforitem(String itemname) {
        for (T item : itemslist) {
            if (item.getTitle().equals(itemname)) {
                return item;
            }
        }
        return null;
    }

    public void displayallitems() {
        for (T item : itemslist) {
            System.out.println(item);
        }
    }

    public void markitemascompleted(String itemname) {
        for (T item : itemslist) {
            if (item.getTitle().equals(itemname) && !item.getWatched()) {
                item.setwatched(true);
                try {
                    Mapper.writeValue(file, itemslist);
                    System.out.println("Item has been marked as completed.");
                } catch (Exception e) {
                    System.out.println("An error occurred while marking the item as completed: " + e.getMessage());
                }
                return;
            }
            else if (item.getTitle().equals(itemname) && item.getWatched() == true) {
                System.out.println("Item is already marked as completed.");
                return;
            }

        }
    }

    public double calculateaverage() {
        double totalRating = 0.0;
        int count = itemslist.size();
        for (T item : itemslist) {
            totalRating += item.getRating();
        }
        double averageRating = totalRating / count;
        return averageRating;

    }
    
}
