package Bookstore;

import Messages.CloseSystemMessage;
import Messages.StreamRequest;
import akka.NotUsed;
import akka.actor.AbstractActor;
import akka.stream.ThrottleMode;
import akka.stream.javadsl.*;
import akka.util.ByteString;
import scala.concurrent.duration.Duration;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

public class StreamActor extends AbstractActor{
    public AbstractActor.Receive createReceive() {
        return receiveBuilder()
                .match(StreamRequest.class, sr -> {
                    final Path file = Paths.get(sr.getTitle() + ".txt");

                    final Source source = FileIO.fromPath(file)
                            .via(Framing.delimiter(ByteString.fromString("\r\n"), 100, FramingTruncation.ALLOW))
                            .throttle(1, Duration.create(1, TimeUnit.SECONDS), 1, ThrottleMode.shaping());

                    final Sink<ByteString, NotUsed> sinkOfLines = Sink.actorRef(getSender(), new CloseSystemMessage());

                    source.runWith(sinkOfLines, Bookstore.materializer);

                })
                .matchAny(o -> System.out.println("Stream actor received unknown message."))
                .build();
    }
}
