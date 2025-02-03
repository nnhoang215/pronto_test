package com.pronto_test;

import java.util.List;
import java.util.ArrayList;
import java.nio.file.*;
import java.io.IOException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.*;

public class Board {
  private List<Tile> tiles; // List to store tiles on the board
  private ObjectMapper objectMapper = new ObjectMapper(); // ObjectMapper for JSON processing
  private String boardUrl; // Given path to the board file

  // Constructor to initialize the board with a given URL
  public Board(String boardUrl) {
    this.tiles = new ArrayList<Tile>();
    this.boardUrl = boardUrl;
  }

  // Returns the size of the board (number of tiles)
  public int getBoardSize() {
    return this.tiles.size();
  }

  // Returns the tile at the specified position
  public Tile getTile(int position) {
    return this.tiles.get(position);
  }

  // Returns the list of tiles on the board
  public List<Tile> getTiles() {
    return tiles;
  }

  // Loads the board from a file
  public void loadBoard() throws IOException {
    try {
      // Read the JSON content from the file
      String json = Files.readString(Paths.get(boardUrl));
      ArrayNode jsonArray = (ArrayNode) objectMapper.readTree(json);

      // Iterate through each JSON node and create corresponding Tile objects
      for (JsonNode jsonNode : jsonArray) {
        String type = jsonNode.get("type").asText();

        if (type.equals("go")) {
          tiles.add(new GoTile(jsonNode.get("name").asText()));
        } else if (type.equals("property")) {
          String name = jsonNode.get("name").asText();
          int price = jsonNode.get("price").asInt();
          String colour = jsonNode.get("colour").asText();
          tiles.add(new PropertyTile(name, price, colour));
        }
      }
    } catch (IOException e) {
      throw e;
    }
  }
}