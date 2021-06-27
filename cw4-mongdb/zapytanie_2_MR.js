var mapFunction1 = function () {
    const credits = this.credit;
    for (let index = 0; index < credits.length; index++) {
        const credit = credits[index];
        emit(credit.currency, {
            balance: parseFloat(credit.balance)
        });
    }
};

var reduceFunction1 = function (keySex, valuesHW) {
    var balanceSum = 0;

    for (let i = 0; i < valuesHW.length; i++) {
        const element = valuesHW[i];
        balanceSum += element.balance;
    }

    return {
        balance: balanceSum
    }
};

printjson(db.people.mapReduce(
    mapFunction1,
    reduceFunction1,
    {
        out: { inline: 1 }
    }
))