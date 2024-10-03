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

}
