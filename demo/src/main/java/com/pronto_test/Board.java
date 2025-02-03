package com.pronto_test;

import java.util.*;
import java.nio.file.*;
import java.io.IOException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.*;

public class Board {
  private List<Tile> tiles;
  private ObjectMapper objectMapper = new ObjectMapper();
  private String boardUrl = "demo/board.json"; // Given path

  public Board() {
    tiles = new ArrayList<Tile>();
  }

  public int getBoardSize() {
    return this.tiles.size();
  }
  public Tile getTile(int position) {
    // TODO: check if the position is valid
    return this.tiles.get(position);
  }

  public List<Tile> getTiles() {
    return tiles;
  }

  // loads the board from a file
  public void loadBoard() throws IOException {
    try {
      String json = Files.readString(Paths.get(boardUrl));
      ArrayNode jsonArray = (ArrayNode) objectMapper.readTree(json);

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