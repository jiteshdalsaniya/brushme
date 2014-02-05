package com.technotricks.paint.manager;

import com.technotricks.paint.R;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.SoundPool;

public class SoundManager
{
  private static SoundManager instance = new SoundManager();
  private static Object lock = new Object();
  private MediaPlayer musicPlayer;
  public int soundButton;
  public int soundPhoto;
 // private SoundPool soundPool;

  public static SoundManager instance()
  {
    synchronized (lock)
    {
      SoundManager localSoundManager = instance;
      return localSoundManager;
    }
  }

  public void load(Context paramContext)
  {
	  
	  if(this.musicPlayer==null)
	  {
    this.musicPlayer = MediaPlayer.create(paramContext, R.raw.music);
    this.musicPlayer.setLooping(true);
	  }
  //  this.soundPool = new SoundPool(10, 3, 0);
 //   this.soundButton = this.soundPool.load(paramContext, 2130903040, 1);
  //  this.soundPhoto = this.soundPool.load(paramContext, 2130903042, 1);
  }

 /* public void playButtonClick(float paramFloat)
  {
    this.soundPool.play(this.soundButton, paramFloat, paramFloat, 1, 0, 1.0F);
  }

  public void playPhotoClick(float paramFloat)
  {
    this.soundPool.play(this.soundPhoto, paramFloat, paramFloat, 1, 0, 1.0F);
  }*/

  public void startBackgroundMusic()
  {
    if (!this.musicPlayer.isPlaying())
    {
      //this.musicPlayer.seekTo(0);
      this.musicPlayer.start();
    }
  }

  public void stopBackgroundMusic()
  {
	  if((this.musicPlayer!=null ) &&(this.musicPlayer.isPlaying()))
	  
    this.musicPlayer.pause();
  }
}

/* Location:           E:\dress up game 2\dressup 3\com.blogspot.strogonov.animechristmasdressupv1.2.3.apk\classes_dex2jar.jar
 * Qualified Name:     com.blogspot.strogonov.animechristmasdressup.SoundManager
 * JD-Core Version:    0.6.2
 */