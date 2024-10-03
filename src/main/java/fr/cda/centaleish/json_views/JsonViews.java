package fr.cda.centaleish.json_views;

public class JsonViews {

    public interface UserMinimalView {}
    public interface UserShowView extends
            UserMinimalView,
            ListingMinimalView,
            FavoriteView {}

    public interface FavoriteView {}

    public interface ListingMinimalView {}
    public interface ListingListView extends ListingMinimalView {}
    public interface ListingShowView extends ListingListView {}

    public interface BrandMinimalView {}
    public interface BrandListView extends BrandMinimalView {}
    public interface BrandShowView extends
            BrandMinimalView,
            ModelMinimalView {}

    public interface ModelMinimalView {}
}
