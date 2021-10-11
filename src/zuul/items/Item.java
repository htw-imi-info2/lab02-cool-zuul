package zuul.items;
public class Item
{
    String description, name;
    int weight;
    public Item(String name, String description, int weight){
        this.name = name;
        this.description = description; 
        this.weight = weight;
    }
    public String getDescription(){
        return description;
    }
    public String getFullDescription(){
        return getFullDescription(name) ;
    }
    public String getFullDescription(String key){
        return description +" ("+key+")";
    }
    public String getName(){
        return name;
    }
    public int getWeight(){
        return weight;
    }
}
