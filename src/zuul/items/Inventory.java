package zuul.items;
import java.util.*;
import java.util.stream.Collectors;
public class Inventory implements Iterable<Item>
{
    Map<String,Item> items = new LinkedHashMap<>();
    public void add(Item item){
        String name = item.getName();
        String key = name;
        for(int n = 0 ; items.containsKey(key); n++,key=name+n);
        items.put(key,item);
    };

    public Item remove(String itemName){
        return items.remove(findItemName(itemName));
    }

    private String findItemName(String itemName){
        if (items.containsKey(itemName)) return itemName;
        String stripped = itemName.replaceFirst("\\d+$","");
        if (items.containsKey(stripped)) return stripped;
        for(String key : items.keySet()){
            if (key.matches(stripped+"\\d+$"))
                return key;
        }
        return null;
    }
    public Item get(String itemName){
        return items.get(findItemName(itemName));
    }

    public String getDescription(){
        if (items.isEmpty()) return "";
        return "Items:\n" + items.keySet().stream().map(k -> " - "+items.get(k).getFullDescription(k)).collect(Collectors.joining("\n"));
    }

    @Override
    public Iterator<Item> iterator() {
        return items.values().iterator();
    }

    public int getWeight() {
        return items.values().stream().reduce(0,(sum,item) -> sum + item.getWeight(), Integer::sum);
    }
}
