package com.domain.project.core;

import static playn.core.PlayN.*;

import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.ResourceCallback;
import playn.core.GroupLayer; 

public class Digits
{	
    private ImageLayer digit1;
	private ImageLayer digit2;
	private ImageLayer digit3;
	private ImageLayer digit4;
	
	private float xPos;
	private float yPos;
	private float scale;
	
	final GroupLayer uiLayer;

    public Digits(final GroupLayer uiLayer, final float xPos, final float yPos, final float scale)
    {
		this.uiLayer = uiLayer;
		this.xPos = xPos;
		this.yPos = yPos;
		this.scale = scale;
		
        digit1 = graphics().createImageLayer(Const.N0_IMAGE);

        Const.N0_IMAGE.addCallback(new ResourceCallback<Image>() {
            @Override
            public void done(Image image) {
				digit1.setTranslation(xPos, yPos);
				digit1.setScale(scale);
				digit1.setDepth(1.0f);
                uiLayer.add(digit1);
            }

            @Override
            public void error(Throwable e) {
                log().error("error loading node", e);
            }
        });

    }
	
	public void setDigits(int digits) {
		String sDigits =  reverse(Integer.toString(digits));
		String sDigit;
		for(int i = 0; i < sDigits.length(); i++) {
			sDigit = sDigits.substring(i, i+1);
			switch(i) {
				case 0 : setFirstDigit(Integer.parseInt(sDigit)); break;
				case 1 : setSecondDigit(Integer.parseInt(sDigit)); break;
				case 2 : setThirdDigit(Integer.parseInt(sDigit)); break;
				case 3 : setFourthDigit(Integer.parseInt(sDigit)); break;
				default : break;
			}
			
		}
	}
	
	private String reverse(String s) {
		return new StringBuffer(s).reverse().toString();
	}
	
	private void setFirstDigit(int digit) {
		if(digit1 != null)
			digit1.destroy();
		digit1 = graphics().createImageLayer(getNumberImage(digit));
		getNumberImage(digit).addCallback(new ResourceCallback<Image>() {
            @Override
            public void done(Image image) {
				digit1.setTranslation(xPos, yPos);
				digit1.setScale(scale);
				digit1.setDepth(1.0f);
                uiLayer.add(digit1);
            }

            @Override
            public void error(Throwable e) {
                log().error("error loading node", e);
            }
        });
	}
	
	private void setSecondDigit(int digit) {
		if(digit2 != null)
			digit2.destroy();
		digit2 = graphics().createImageLayer(getNumberImage(digit));
		getNumberImage(digit).addCallback(new ResourceCallback<Image>() {
            @Override
            public void done(Image image) {
				digit2.setTranslation(xPos - 35.0f, yPos);
				digit2.setScale(scale);
				digit2.setDepth(1.0f);
                uiLayer.add(digit2);
            }

            @Override
            public void error(Throwable e) {
                log().error("error loading node", e);
            }
        });
	}
	
	private void setThirdDigit(int digit) {
		if(digit3 != null)
			digit3.destroy();
		digit3 = graphics().createImageLayer(getNumberImage(digit));
		getNumberImage(digit).addCallback(new ResourceCallback<Image>() {
            @Override
            public void done(Image image) {
				digit3.setTranslation(xPos - 65.0f, yPos);
				digit3.setScale(scale);
				digit3.setDepth(1.0f);
                uiLayer.add(digit3);
            }

            @Override
            public void error(Throwable e) {
                log().error("error loading node", e);
            }
        });
	}
	
	private void setFourthDigit(int digit) {
		if(digit4 != null)
			digit4.destroy();
		digit4 = graphics().createImageLayer(getNumberImage(digit));
		getNumberImage(digit).addCallback(new ResourceCallback<Image>() {
            @Override
            public void done(Image image) {
				digit4.setTranslation(xPos - 95.0f, yPos);
				digit4.setScale(scale);
				digit4.setDepth(1.0f);
                uiLayer.add(digit4);
            }

            @Override
            public void error(Throwable e) {
                log().error("error loading node", e);
            }
        });
	}
	
	private Image getNumberImage(int digit) {
		switch(digit) {
			case 1 : return Const.N1_IMAGE;
			case 2 : return Const.N2_IMAGE;
			case 3 : return Const.N3_IMAGE;
			case 4 : return Const.N4_IMAGE;
			case 5 : return Const.N5_IMAGE;
			case 6 : return Const.N6_IMAGE;
			case 7 : return Const.N7_IMAGE;
			case 8 : return Const.N8_IMAGE;
			case 9 : return Const.N9_IMAGE;
			default : return Const.N0_IMAGE;
		}
	}
}
