import Laboratory


class ReporterI(Laboratory.Reporter):
    def report(self, msg, curr):
        print(msg)
