package io.github.wkktoria.httpcodes;

import io.github.wkktoria.httpcodes.gui.App;

class HttpCodes {
    public static void main(String[] args) {

        if (args.length == 1) {
            io.github.wkktoria.httpcodes.cli.App app =
                    new io.github.wkktoria.httpcodes.cli.App(args[0]);
            app.run();

            return;
        }

        new App();
    }
}
