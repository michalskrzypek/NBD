class KontoBankowe(stan: Double) {

  def this() {
    this(0)
  }

  private var _stan: Double = stan

  def stan: Double = _stan

  def wyplac(kwota: Double): Double = {
    if (_stan - kwota < 0) return _stan

    _stan = _stan - kwota
    _stan
  }

  def wplac(kwota: Double): Double = {
    _stan = _stan + kwota
    _stan
  }
}
