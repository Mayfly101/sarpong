import java.util.*;

// Define the item category enumeration
enum ItemCategory {
    Beverages,
    BreadBakery,
    CannedJarredGoods,
    DairyProducts,
    DryBakingGood,
    FrozenProducts,
    Meat,
    FarmProduce,
    HomeCleaners,
    PaperGoods,
    HomeCare,
}

// Define the item class
class Item {
    private String name;
    private double price;
    private ItemCategory category;

    public Item(String name, double price, ItemCategory category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public ItemCategory getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "Item [name=" + name + ", price=" + price + ", category=" + category + "]";
    }
}

// Define the vendor class
class Vendor {
    private String name;
    private Map<String, Item> products;

    public Vendor(String name) {
        this.name = name;
        this.products = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public Map<String, Item> getProducts() {
        return products;
    }

    public void addProduct(String code, Item item) {
        products.put(code, item);
    }

    @Override
    public String toString() {
        return "Vendor [name=" + name + ", products=" + products + "]";
    }
}

// Define the inventory management system class
class InventoryManagementSystem {
    private Map<ItemCategory, Stack<Item>> categoryStacks;
    private Map<ItemCategory, Queue<Item>> categoryQueues;
    private Map<ItemCategory, List<Item>> categoryLists;
    private Map<String, Vendor> vendors;
    private Set<String> soldProducts;
    
    public InventoryManagementSystem() {
        categoryStacks = new HashMap<>();
        categoryQueues = new HashMap<>();
        categoryLists = new HashMap<>();
        vendors = new HashMap<>();
        soldProducts = new HashSet<>();

        // Initialize stacks, queues, and lists for each category
        for (ItemCategory category : ItemCategory.values()) {
            categoryStacks.put(category, new Stack<>());
            categoryQueues.put(category, new LinkedList<>());
            categoryLists.put(category, new ArrayList<>());
        }
    }

    public void addItem(Item item) {
        switch (item.getCategory()) {
            case Beverages:
            case BreadBakery:
            case CannedJarredGoods:
            case DairyProducts:
                categoryStacks.get(item.getCategory()).push(item);
                break;
            case DryBakingGood:
            case FrozenProducts:
            case Meat:
                categoryQueues.get(item.getCategory()).offer(item);
                break;
            case FarmProduce:
            case HomeCleaners:
            case PaperGoods:
            case HomeCare:
                categoryLists.get(item.getCategory()).add(item);
                break;
            default:
                throw new IllegalArgumentException("Invalid item category.");
        }
    }

    public void removeItem(Item item) {
        switch (item.getCategory()) {
            case Beverages:
            case BreadBakery:
            case CannedJarredGoods:
            case DairyProducts:
                categoryStacks.get(item.getCategory()).pop();
                break;
            case DryBakingGood:
            case FrozenProducts:
            case Meat:
                categoryQueues.get(item.getCategory()).poll();
                break;
            case FarmProduce:
            case HomeCleaners:
            case PaperGoods:
            case HomeCare:
                categoryLists.get(item.getCategory()).remove(item);
                break;
            default:
                throw new IllegalArgumentException("Invalid item category.");
        }
    }

    public void addVendor(String name, Map<String, Item> products) {
        vendors.put(name, new Vendor(name));
        Vendor vendor = vendors.get(name);
        vendor.getProducts().putAll(products);
    }

    public void sellItem(String productCode) {
        soldProducts.add(productCode);
    }

    public void generateSalesReport() {
        System.out.println("Sales Report:");
        for (Vendor vendor : vendors.values()) {
            int soldCount = 0;
            for (Item item : vendor.getProducts().values()) {
                if (soldProducts.contains(item.getName())) {
                    soldCount++;
                }
            }
            System.out.println("Vendor: " + vendor.getName() + ", Sold Count: " + soldCount);
        }
    }
}

// Example usage
public class JavaInventoryManagementSystem {
    public static void main(String[] args) {
        // Create an inventory management system instance
        InventoryManagementSystem inventorySystem = new InventoryManagementSystem();

        // Add some items
        Item item1 = new Item("Coffee", 2.99, ItemCategory.Beverages);
        Item item2 = new Item("Sandwich Loaves", 1.99, ItemCategory.BreadBakery);
        Item item3 = new Item("Vegetables", 0.99, ItemCategory.CannedJarredGoods);

        inventorySystem.addItem(item1);
        inventorySystem.addItem(item2);
        inventorySystem.addItem(item3);

        // Add a vendor
        Map<String, Item> vendorProducts = new HashMap<>();
        vendorProducts.put("P1", new Item("Juice", 1.49, ItemCategory.Beverages));
        vendorProducts.put("P1", new Item("Soda", 1.49, ItemCategory.Beverages));
        vendorProducts.put("P1", new Item("Energy Drinks", 1.49, ItemCategory.Beverages));
        vendorProducts.put("P1", new Item("Liquor", 1.49, ItemCategory.Beverages));
        vendorProducts.put("P1", new Item("Beer", 1.49, ItemCategory.Beverages));
        vendorProducts.put("P1", new Item("Kombucha", 1.49, ItemCategory.Beverages));
        vendorProducts.put("P1", new Item("Soft Drinks", 1.49, ItemCategory.Beverages));
        vendorProducts.put("P1", new Item("Teebags", 1.49, ItemCategory.Beverages));
        vendorProducts.put("P2", new Item("Dinner Rolls", 0.99, ItemCategory.BreadBakery));
        vendorProducts.put("P1", new Item("Tortillas", 1.49, ItemCategory.BreadBakery));
        vendorProducts.put("P1", new Item("Bagels", 1.49, ItemCategory.BreadBakery));
        vendorProducts.put("P1", new Item("Muffins", 1.49, ItemCategory.BreadBakery));
        vendorProducts.put("P1", new Item("Donuts", 1.49, ItemCategory.BreadBakery));
        vendorProducts.put("P1", new Item("Cookies", 1.49, ItemCategory.BreadBakery));
        vendorProducts.put("P1", new Item("Bread", 1.49, ItemCategory.BreadBakery));
        vendorProducts.put("P1", new Item("Wraps", 1.49, ItemCategory.BreadBakery));
        vendorProducts.put("P1", new Item("Hot Dog Buns", 1.49, ItemCategory.BreadBakery));
        vendorProducts.put("P1", new Item("Bagels", 1.49, ItemCategory.BreadBakery));
        vendorProducts.put("P1", new Item("Spaghetti Sauce", 1.49, ItemCategory.CannedJarredGoods));
        vendorProducts.put("P1", new Item("Ketchup", 1.49, ItemCategory.CannedJarredGoods));
        vendorProducts.put("P1", new Item("Apple Sauce", 1.49, ItemCategory.CannedJarredGoods));
        vendorProducts.put("P1", new Item("Baked Sauce", 1.49, ItemCategory.CannedJarredGoods));
        vendorProducts.put("P1", new Item("Black Beans", 1.49, ItemCategory.CannedJarredGoods));
        vendorProducts.put("P1", new Item("Canned Fruit", 1.49, ItemCategory.CannedJarredGoods));
        vendorProducts.put("P1", new Item("Creamed Corn", 1.49, ItemCategory.CannedJarredGoods));
        vendorProducts.put("P1", new Item("Cheeses", 1.49, ItemCategory.DairyProducts));
        vendorProducts.put("P1", new Item("Eggs", 1.49, ItemCategory.DairyProducts));
        vendorProducts.put("P1", new Item("Milk", 1.49, ItemCategory.DairyProducts));
        vendorProducts.put("P1", new Item("Yoghurt", 1.49, ItemCategory.DairyProducts));
        vendorProducts.put("P1", new Item("Butter", 1.49, ItemCategory.DairyProducts));
        vendorProducts.put("P1", new Item("Sour Cream", 1.49, ItemCategory.DairyProducts));
        vendorProducts.put("P1", new Item("Cream Cheese", 1.49, ItemCategory.DairyProducts));
        vendorProducts.put("P1", new Item("Cheddar Cheese", 1.49, ItemCategory.DairyProducts));
        vendorProducts.put("P1", new Item("Whipped Cream", 1.49, ItemCategory.DairyProducts));
        vendorProducts.put("P1", new Item("Cereals", 1.49, ItemCategory.DryBakingGood));
        vendorProducts.put("P1", new Item("Flour", 1.49, ItemCategory.DryBakingGood));
        vendorProducts.put("P1", new Item("Sugar", 1.49, ItemCategory.DryBakingGood));
        vendorProducts.put("P1", new Item("Pasta", 1.49, ItemCategory.DryBakingGood));
        vendorProducts.put("P1", new Item("Mixes", 1.49, ItemCategory.DryBakingGood));
        vendorProducts.put("P1", new Item("Quinoa", 1.49, ItemCategory.DryBakingGood));
        vendorProducts.put("P1", new Item("Oats", 1.49, ItemCategory.DryBakingGood));
        vendorProducts.put("P1", new Item("Curry Powder", 1.49, ItemCategory.DryBakingGood));
        vendorProducts.put("P1", new Item("Waffles", 1.49, ItemCategory.FrozenProducts));
        vendorProducts.put("P1", new Item("Vegetables", 1.49, ItemCategory.FrozenProducts));
        vendorProducts.put("P1", new Item("Individual Meals", 1.49, ItemCategory.FrozenProducts));
        vendorProducts.put("P1", new Item("Ice Cream", 1.49, ItemCategory.FrozenProducts));
        vendorProducts.put("P1", new Item("Ice Pops", 1.49, ItemCategory.FrozenProducts));
        vendorProducts.put("P1", new Item("Veggie Burgers", 1.49, ItemCategory.FrozenProducts));
        vendorProducts.put("P1", new Item("Chopped Fruits", 1.49, ItemCategory.FrozenProducts));
        vendorProducts.put("P1", new Item("Fish", 1.49, ItemCategory.FrozenProducts));
        vendorProducts.put("P1", new Item("Pies", 1.49, ItemCategory.FrozenProducts));
        vendorProducts.put("P1", new Item("Lunch Meat", 1.49, ItemCategory.Meat));
        vendorProducts.put("P1", new Item("Poultry", 1.49, ItemCategory.Meat));
        vendorProducts.put("P1", new Item("Beef", 1.49, ItemCategory.Meat));
        vendorProducts.put("P1", new Item("Pork", 1.49, ItemCategory.Meat));
        vendorProducts.put("P1", new Item("Sausage", 1.49, ItemCategory.Meat));
        vendorProducts.put("P1", new Item("Hot Dogs", 1.49, ItemCategory.Meat));
        vendorProducts.put("P1", new Item("Steak", 1.49, ItemCategory.Meat));
        vendorProducts.put("P1", new Item("Ham", 1.49, ItemCategory.Meat));
        vendorProducts.put("P1", new Item("Deli Meat", 1.49, ItemCategory.Meat));
        vendorProducts.put("P1", new Item("Fruits", 1.49, ItemCategory.FarmProduce));
        vendorProducts.put("P1", new Item("Vegetables", 1.49, ItemCategory.FarmProduce));
        vendorProducts.put("P1", new Item("All-Purpose", 1.49, ItemCategory.HomeCleaners));
        vendorProducts.put("P1", new Item("Laundry Detergent", 1.49, ItemCategory.HomeCleaners));
        vendorProducts.put("P1", new Item("Dishwashing Liquid/Detergent", 1.49, ItemCategory.HomeCleaners));
        vendorProducts.put("P1", new Item("Dish Soap", 1.49, ItemCategory.HomeCleaners));
        vendorProducts.put("P1", new Item("Fabric Softener", 1.49, ItemCategory.HomeCleaners));
        vendorProducts.put("P1", new Item("Floor Cleaner", 1.49, ItemCategory.HomeCleaners));
        vendorProducts.put("P1", new Item("Glass Spray", 1.49, ItemCategory.HomeCleaners));
        vendorProducts.put("P1", new Item("Bleach", 1.49, ItemCategory.HomeCleaners));
        vendorProducts.put("P1", new Item("Paper Towels", 1.49, ItemCategory.PaperGoods));
        vendorProducts.put("P1", new Item("Toilet Paper", 1.49, ItemCategory.PaperGoods));
        vendorProducts.put("P1", new Item("Aluminium Foil", 1.49, ItemCategory.PaperGoods));
        vendorProducts.put("P1", new Item("Sandwich Bags", 1.49, ItemCategory.PaperGoods));
        vendorProducts.put("P1", new Item("Sandwich Bags", 1.49, ItemCategory.PaperGoods));
        vendorProducts.put("P1", new Item("Baby Wipes", 1.49, ItemCategory.PaperGoods));
        vendorProducts.put("P1", new Item("Diapers", 1.49, ItemCategory.PaperGoods));
        vendorProducts.put("P1", new Item("Poop Bags", 1.49, ItemCategory.PaperGoods));
        vendorProducts.put("P1", new Item("Vacuum Bags", 1.49, ItemCategory.PaperGoods));
        vendorProducts.put("P1", new Item("Waxed Bags", 1.49, ItemCategory.PaperGoods));
        vendorProducts.put("P1", new Item("Plastic Bags", 1.49, ItemCategory.PaperGoods));
        vendorProducts.put("P1", new Item("Soap", 1.49, ItemCategory.HomeCare));
        vendorProducts.put("P1", new Item("Hand Soap", 1.49, ItemCategory.HomeCare));
        vendorProducts.put("P1", new Item("Shaving Cream", 1.49, ItemCategory.HomeCare));
        vendorProducts.put("P1", new Item("Shaving Cream", 1.49, ItemCategory.HomeCare));
        vendorProducts.put("P1", new Item("Body Lotion", 1.49, ItemCategory.HomeCare));
        vendorProducts.put("P1", new Item("Deodorant", 1.49, ItemCategory.HomeCare));
        vendorProducts.put("P1", new Item("Conditioner", 1.49, ItemCategory.HomeCare));
        vendorProducts.put("P1", new Item("Cotton Swabs", 1.49, ItemCategory.HomeCare));
        vendorProducts.put("P1", new Item("Toothpaste", 1.49, ItemCategory.HomeCare));

        inventorySystem.addVendor("Vendor A", vendorProducts);

        // Sell an item
        inventorySystem.sellItem("P1");

        // Generate sales report
        inventorySystem.generateSalesReport();
    }
}
