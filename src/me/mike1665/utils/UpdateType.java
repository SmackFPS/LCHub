package me.mike1665.utils;


public enum UpdateType
{
  MIN_64(3840000L), 
  MIN_32(1920000L), 
  MIN_16(960000L), 
  MIN_08(480000L), 
  MIN_04(240000L), 
  MIN_02(120000L), 
  MIN_01(60000L), 
  SLOWEST(32000L), 
  SLOWER(16000L), 
  SLOW(4000L), 
  SEC(1000L), 
  FAST(500L), 
  FASTER(250L), 
  FASTEST(125L), 
  TICK(49L);

  @SuppressWarnings("unused")
private long _time;
  @SuppressWarnings("unused")
private long _last;
  @SuppressWarnings("unused")
private long _timeSpent;
  @SuppressWarnings("unused")
private long _timeCount;

  private UpdateType(long time) { this._time = time;
    this._last = System.currentTimeMillis();
  }
}