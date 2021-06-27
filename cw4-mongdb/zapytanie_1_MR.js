var mapFunction1 = function () {
    emit(this.sex, {
        height: parseFloat(this.height),
        weight: parseFloat(this.weight),
        count: 1
    });
};

var reduceFunction1 = function (keySex, valuesHW) {
    var heightSum = 0;
    var weightSum = 0;
    var totalCount = 0;

    for (let i = 0; i < valuesHW.length; i++) {
        const element = valuesHW[i];
        heightSum += element.height;
        weightSum += element.weight;
        totalCount += element.count;
    }

    return {
        height: heightSum,
        weight: weightSum,
        count: totalCount
    }
};

var finalizeFunction1 = function (keySex, reducedVal) {
    avgHeight = reducedVal.height / reducedVal.count;
    avgWeight = reducedVal.weight / reducedVal.count;
    return { 
        avgHeight: avgHeight,
        avgWeight: avgWeight
    };
};

printjson(db.people.mapReduce(
    mapFunction1,
    reduceFunction1,
    {
        finalize: finalizeFunction1,
        out: { inline: 1 }
    }
))