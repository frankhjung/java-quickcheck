package com.marlo.quicktheories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** Hello world! */
public class App {

  public static void main(String[] args) {
    Logger log = LoggerFactory.getLogger(App.class);
    if (args.length > 0) {
      log.error("Found {} arguments, none expected.", args.length);
    }
  }
}
