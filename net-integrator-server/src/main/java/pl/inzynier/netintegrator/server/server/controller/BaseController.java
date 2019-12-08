package pl.inzynier.netintegrator.server.server.controller;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import pl.inzynier.netintegrator.http.spark.SparkController;


@RequiredArgsConstructor
public abstract class BaseController implements SparkController {

    protected final Gson gson;
}
