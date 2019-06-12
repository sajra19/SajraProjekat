package ba.ibu.sajraprojekat.Model;


    public class Food {
        private String Name, Images, Price, Discount, MenuId, Description;

        public Food() {
        }

        public Food(String name, String images, String price, String discount, String menuId, String description) {
            Name = name;
            Images = images;
            Price = price;
            Discount = discount;
            MenuId = menuId;
            Description = description;
        }

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

        public String getImages() {
            return Images;
        }

        public void setImages(String images) {
            Images = images;
        }

        public String getPrice() {
            return Price;
        }

        public void setPrice(String price) {
            Price = price;
        }

        public String getDiscount() {
            return Discount;
        }

        public void setDiscount(String discount) {
            Discount = discount;
        }

        public String getMenuId() {
            return MenuId;
        }

        public void setMenuId(String menuId) {
            MenuId = menuId;
        }

        public String getDescription() {
            return Description;
        }

        public void setDescription(String description) {
            Description = description;
        }
    }