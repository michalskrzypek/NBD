var mapFunction1 = function () {
    emit(this.job, {
    });
};

var reduceFunction1 = function (keyJob, values) {
    return {}
};

var finalizeFunction1 = function (keyJob, reducedVal) {
    return keyJob;
};

printjson(db.people.mapReduce(
    mapFunction1,
    reduceFunction1,
    {
        finalize: finalizeFunction1,
        out: { inline: 1 }
    }
))