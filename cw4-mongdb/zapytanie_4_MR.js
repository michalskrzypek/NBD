var mapFunction1 = function () {
    const heightInM = parseFloat(this.height) / 100;
    const heightInM2 = heightInM * heightInM;
    const bmi = this.weight / heightInM2;
    emit(this.nationality, bmi);
};

var reduceFunction1 = function (keyNationality, valuesBMI) {
    return valuesBMI
};

var finalizeFunction1 = function (keyNationality, reducedVal) {
    const bmiMin = Math.min(...reducedVal);
    const bmiMax = Math.max(...reducedVal);
    const bmiAvg = Array.avg(reducedVal);
    return {
        BMI: {
            min: bmiMin,
            max: bmiMax,
            avg: bmiAvg
        }
    }
};

printjson(db.people.mapReduce(
    mapFunction1,
    reduceFunction1,
    {
        finalize: finalizeFunction1,
        out: { inline: 1 }
    }
))