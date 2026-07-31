package com.grazyarcadeback.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameResultRequest {
  private String winnerNickname;
  private List<String> loserNicknames;
}