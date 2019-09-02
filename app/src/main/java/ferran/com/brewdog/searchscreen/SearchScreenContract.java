package ferran.com.brewdog.searchscreen;

public interface SearchScreenContract {
    interface View {
        void navigateResultsScreen();

        void showError(String message);

        void showComplete();
    }

    interface Presenter {
        void loadBeers(String food);
    }
}
