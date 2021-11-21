class Y {
  void m(){
    Exception e = new Exception("got you this time");
    try {
      throw e;
    } catch( e ) {
      System.out.println(e.getMessage());
      throw e;
    }
  }
}
