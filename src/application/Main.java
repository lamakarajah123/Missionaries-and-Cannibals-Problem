package application;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    private TextArea output;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Label Label1 = new Label("Missionaries and Cannibals Problem");
        primaryStage.setWidth(1000);
        primaryStage.setHeight(800);

        ComboBox<String> search = new ComboBox<>();
        search.getItems().addAll("Breadth First Search", "Depth Limited Search");
        search.setPromptText("Select Search Method");

        State inState = new State(3, 3, Position.LEFT, 0, 0);

        Button ex = new Button("Execute");
        ex.setOnAction(e -> exSearch(inState, search.getSelectionModel().getSelectedItem())); // Use getSelectedItem() instead of getValue()

        output = new TextArea();
        output.setEditable(false);
        output.setPrefWidth(600);
        output.setPrefHeight(400);

        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(Label1, search, ex, output);

        Scene scene = new Scene(root, 800, 800);
        primaryStage.setTitle("Missionaries and Cannibals Problem");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void exSearch(State inState, String searchM) {
        if (searchM == null || searchM.isEmpty()) {
            appendText("Please select a search method.");
            return;
        }

        long sTime = System.currentTimeMillis();

        State sol = null;
        if ("Breadth First Search".equals(searchM)) {
            BFS bfsSearch = new BFS();
            appendText("\nThis is Breadth First Search");
            sol = bfsSearch.exec(inState);
        } else if ("Depth Limited Search".equals(searchM)) {
            appendText("\nThis is Depth Limited Search");
            DFS dfsSearch = new DFS();
            sol = dfsSearch.exec(inState);
        } else {
            appendText("ERROR: Unknown search method.");
        }

        long eTime = System.currentTimeMillis();
        long exTime = eTime - sTime;

        print(sol, exTime);
    }

    private void print(State sol, long exTime) {
        if (sol == null) {
            appendText("No solution found.");
        } else {
            appendText("Solution (cannibalLeft, missionaryLeft, boat, cannibalRight, missionaryRight): ");

            java.util.List<State> path = new java.util.ArrayList<>();
            State state = sol;
            while (state != null) {
                path.add(state);
                state = state.getpState();
            }

            int depth = path.size() - 1;
            for (int i = depth; i >= 0; i--) {
                state = path.get(i);
                appendText(state.toString() + (i == 0 || state.isGoal()));
            }

            appendText("\nDepth: " + depth);
            appendText("Execution Time: " + exTime + " milliseconds");
        }
    }

    private void appendText(String text) {
        output.appendText(text + "\n");
    }
}
