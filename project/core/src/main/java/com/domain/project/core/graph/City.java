package com.domain.project.core.graph;

import static playn.core.PlayN.*;

import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.GroupLayer;
import playn.core.ResourceCallback;

import com.domain.project.core.Const;

public class City extends Base
{
	private ImageLayer palace;
	private int city_level;
	//TODO : add houses

	//private ImageLayer tavern;
	//private int tavern_level;

	private ImageLayer bazar_food;
	private ImageLayer bazar_china;
	private ImageLayer bazar_carpet;
	private int bazar_level;

	private ImageLayer garden;
	private int garden_level;
	
	private ImageLayer smithy;
	private int smithy_level;

	private ImageLayer tower1;
	private ImageLayer tower2;
	private ImageLayer tower3;
	private ImageLayer tower4;
	private ImageLayer tower_gate1;
	private ImageLayer tower_gate2;
	private int tower_level;

	private ImageLayer wall_front;
	private ImageLayer wall_back;
	private ImageLayer wall_right;
	private ImageLayer wall_left;
	private int wall_level;

	private boolean hasBazaar;
	private boolean hasCaravan;
	private int id;

	public City(GroupLayer graphLayer, int id) {
		super(graphLayer, Const.CITY_BASE_IMAGE);
		city_level = 0;
		bazar_level = 0;
		garden_level = 0;
		tower_level = 0;
		wall_level = 0;
		smithy_level = 0;
		this.id = id;
		hasBazaar = false;
		hasCaravan = false;
	}

	@Override
	public void buildPalace(final GroupLayer graphLayer, Image palace_image) {
		if(palace != null) {
			palace.destroy();
		}

		palace = graphics().createImageLayer(palace_image);
		palace.setDepth(Const.PALACE_DEPTH);

		palace_image.addCallback(new ResourceCallback<Image>() {
			@Override
			public void done(Image image) {
				palace.setOrigin(image.width()/2f, image.height()/2f);
				graphLayer.add(palace);
			}

			@Override
			public void error(Throwable e) {
				log().error("error loading palace", e);
			}
		});
	}

	public int getId() {
		return id;
	}

	@Override
	public int getCityLevel() {
		return city_level;
	}

	@Override
	public void setCityLevel(int level) {
		city_level = level;
	}

	@Override
	public void buildBazarFood(final GroupLayer graphLayer, Image bazar_food_image) {
		if (bazar_food != null) {
			bazar_food.destroy();
		}

		bazar_food = graphics().createImageLayer(bazar_food_image);
		bazar_food.setDepth(Const.BAZAR_FOOD_DEPTH);

		bazar_food_image.addCallback(new ResourceCallback<Image>() {
			@Override
			public void done(Image image) {
				bazar_food.setOrigin(image.width()/2f, image.height()/2f);
				graphLayer.add(bazar_food);
			}

			@Override
			public void error(Throwable e) {
				log().error("error loading node", e);
			}
		});

		hasBazaar = true;
	}

	@Override
	public void buildBazarChina(final GroupLayer graphLayer, Image bazar_china_image) {
		if (bazar_china != null) {
			bazar_china.destroy();
		}

		bazar_china = graphics().createImageLayer(bazar_china_image);
		bazar_china.setDepth(Const.BAZAR_CHINA_DEPTH);

		bazar_china_image.addCallback(new ResourceCallback<Image>() {
			@Override
			public void done(Image image) {
				bazar_china.setOrigin(image.width()/2f, image.height()/2f);
				graphLayer.add(bazar_china);
			}

			@Override
			public void error(Throwable e) {
				log().error("error loading node", e);
			}
		});

		hasBazaar = true;
	}

	@Override
	public void buildBazarCarpet(final GroupLayer graphLayer, Image bazar_carpet_image) {
		if (bazar_carpet != null) {
			bazar_carpet.destroy();
		}

		bazar_carpet = graphics().createImageLayer(bazar_carpet_image);
		bazar_carpet.setDepth(Const.BAZAR_CARPET_DEPTH);

		bazar_carpet_image.addCallback(new ResourceCallback<Image>() {
			@Override
			public void done(Image image) {
				bazar_carpet.setOrigin(image.width()/2f, image.height()/2f);
				graphLayer.add(bazar_carpet);
			}

			@Override
			public void error(Throwable e) {
				log().error("error loading node", e);
			}
		});

		hasBazaar = true;
	}

	@Override
	public int getBazarLevel() {
		return bazar_level;
	}

	@Override
	public void setBazarLevel(int level) {
		bazar_level = level;
	}

	@Override
	public void buildGarden(final GroupLayer graphLayer, Image garden_image) {
		if(garden != null)
			garden.destroy();

		garden = graphics().createImageLayer(garden_image);
		garden.setDepth(Const.GARDEN_DEPTH);

		garden_image.addCallback(new ResourceCallback<Image>() {
			@Override
			public void done(Image image) {
				garden.setOrigin(image.width()/2f, image.height()/2f);
				graphLayer.add(garden);
			}

			@Override
			public void error(Throwable e) {
				log().error("error loading node", e);
			}
		});
	}
	
	@Override
	public int getGardenLevel() {
		return garden_level;
	}

	@Override
	public void setGardenLevel(int level) {
		garden_level = level;
	}
	
	@Override
	public void buildSmithy(final GroupLayer graphLayer, Image smithy_image) {
		if(smithy != null)
			smithy.destroy();

		smithy = graphics().createImageLayer(smithy_image);
		smithy.setDepth(Const.SMITHY_DEPTH);

		smithy_image.addCallback(new ResourceCallback<Image>() {
			@Override
			public void done(Image image) {
				smithy.setOrigin(image.width()/2f, image.height()/2f);
				graphLayer.add(smithy);
			}

			@Override
			public void error(Throwable e) {
				log().error("error loading node", e);
			}
		});
	}

	@Override
	public int getSmithyLevel() {
		return smithy_level;
	}

	@Override
	public void setSmithyLevel(int level) {
		smithy_level = level;
	}

	@Override
	public void buildTower(final GroupLayer graphLayer, Image tower_image) {
		if(tower1 != null)
			tower1.destroy();
		if(tower2 != null)
			tower2.destroy();
		if(tower3 != null)
			tower3.destroy();
		if(tower4 != null)
			tower4.destroy();
		if(tower_gate1 != null)
			tower_gate1.destroy();
		if(tower_gate2 != null)
			tower_gate2.destroy();

		tower1 = graphics().createImageLayer(tower_image);
		tower1.setDepth(Const.TOWER_FRONT_DEPTH);
		tower2 = graphics().createImageLayer(tower_image);
		tower2.setDepth(Const.TOWER_FRONT_DEPTH);
		tower_gate1 = graphics().createImageLayer(tower_image);
		tower_gate1.setDepth(Const.TOWER_FRONT_DEPTH);
		tower_gate2 = graphics().createImageLayer(tower_image);
		tower_gate2.setDepth(Const.TOWER_FRONT_DEPTH);
		tower3 = graphics().createImageLayer(tower_image);
		tower3.setDepth(Const.TOWER_BACK_DEPTH);
		tower4 = graphics().createImageLayer(tower_image);
		tower4.setDepth(Const.TOWER_BACK_DEPTH);

		tower_image.addCallback(new ResourceCallback<Image>() {
			@Override
			public void done(Image image) {
				tower1.setOrigin(image.width()/2f, image.height()/2f);
				graphLayer.add(tower1);
				tower2.setOrigin(image.width()/2f, image.height()/2f);
				graphLayer.add(tower2);
				tower_gate1.setOrigin(image.width()/2f, image.height()/2f);
				graphLayer.add(tower_gate1);
				tower_gate2.setOrigin(image.width()/2f, image.height()/2f);
				graphLayer.add(tower_gate2);
				tower3.setOrigin(image.width()/2f, image.height()/2f);
				graphLayer.add(tower3);
				tower4.setOrigin(image.width()/2f, image.height()/2f);
				graphLayer.add(tower4);
			}

			@Override
			public void error(Throwable e) {
				log().error("error loading node", e);
			}
		});
	}

	@Override
	public int getTowerLevel() {
		return tower_level;
	}

	@Override
	public void setTowerLevel(int level) {
		tower_level = level;
	}

	@Override
	public void buildWall(final GroupLayer graphLayer, Image wall_front_image, Image wall_back_image, Image wall_right_image, Image wall_left_image) {
		if(wall_front != null)
			wall_front.destroy();
		if(wall_back != null)
			wall_back.destroy();
		if(wall_right != null)
			wall_right.destroy();
		if(wall_left != null)
			wall_left.destroy();

		wall_front = graphics().createImageLayer(wall_front_image);
		wall_front.setDepth(Const.WALL_FRONT_DEPTH);
		wall_back = graphics().createImageLayer(wall_back_image);
		wall_back.setDepth(Const.WALL_BACK_DEPTH);
		wall_right = graphics().createImageLayer(wall_right_image);
		wall_right.setDepth(Const.WALL_BACK_DEPTH);
		wall_left = graphics().createImageLayer(wall_left_image);
		wall_left.setDepth(Const.WALL_BACK_DEPTH);

		wall_front_image.addCallback(new ResourceCallback<Image>() {
			@Override
			public void done(Image image) {
				wall_front.setOrigin(image.width()/2f, image.height()/2f);
				graphLayer.add(wall_front);
			}

			@Override
			public void error(Throwable e) {
				log().error("error loading node", e);
			}
		});
		wall_back_image.addCallback(new ResourceCallback<Image>() {
			@Override
			public void done(Image image) {
				wall_back.setOrigin(image.width()/2f, image.height()/2f);
				wall_back.setAlpha(0.75f);
				graphLayer.add(wall_back);
			}

			@Override
			public void error(Throwable e) {
				log().error("error loading node", e);
			}
		});
		wall_right_image.addCallback(new ResourceCallback<Image>() {
			@Override
			public void done(Image image) {
				wall_right.setOrigin(image.width()/2f, image.height()/2f);
				graphLayer.add(wall_right);
			}

			@Override
			public void error(Throwable e) {
				log().error("error loading node", e);
			}
		});
		wall_left_image.addCallback(new ResourceCallback<Image>() {
			@Override
			public void done(Image image) {
				wall_left.setOrigin(image.width()/2f, image.height()/2f);
				graphLayer.add(wall_left);
			}

			@Override
			public void error(Throwable e) {
				log().error("error loading node", e);
			}
		});
	}

	@Override
	public int getWallLevel() {
		return wall_level;
	}

	@Override
	public void setWallLevel(int level) {
		wall_level = level;
	}

	public boolean hasBazaar() {
		return hasBazaar;
	}

	public void setHasBazaar(boolean hasBazaar) {
		this.hasBazaar = hasBazaar;
	}
	
	public boolean hasCaravan() {
		return hasCaravan;
	}

	public void setHasCaravan(boolean hasCaravan) {
		this.hasCaravan = hasCaravan;
	}
	
	@Override
	public void paint(float x, float y) {
		super.paint(x, y);
		if(palace != null) {
			palace.setScale(Const.PALACE_SCALE, Const.PALACE_SCALE);
			palace.setTranslation(x + Const.PALACE_X, y + Const.PALACE_Y);
		}
		if(bazar_food != null) {
			bazar_food.setScale(Const.BAZAR_FOOD_SCALE, Const.BAZAR_FOOD_SCALE);
			bazar_food.setTranslation(x + Const.BAZAR_FOOD_X, y + Const.BAZAR_FOOD_Y);
		}
		if(bazar_china != null) {
			bazar_china.setScale(Const.BAZAR_CHINA_SCALE, Const.BAZAR_CHINA_SCALE);
			bazar_china.setTranslation(x + Const.BAZAR_CHINA_X, y + Const.BAZAR_CHINA_Y);
		}
		if(bazar_carpet != null) {
			bazar_carpet.setScale(Const.BAZAR_CARPET_SCALE, Const.BAZAR_CARPET_SCALE);
			bazar_carpet.setTranslation(x + Const.BAZAR_CARPET_X, y + Const.BAZAR_CARPET_Y);
		}
		if(garden != null) {
			garden.setScale(Const.GARDEN_SCALE, Const.GARDEN_SCALE);
			garden.setTranslation(x + Const.GARDEN_X, y + Const.GARDEN_Y);
		}
		if(smithy != null) {
			smithy.setScale(Const.SMITHY_SCALE, Const.SMITHY_SCALE);
			smithy.setTranslation(x + Const.SMITHY_X, y + Const.SMITHY_Y);
		}
		if(tower1 != null) {
			tower1.setScale(Const.TOWER_FRONT_SCALE, Const.TOWER_FRONT_SCALE);
			tower1.setTranslation(x + Const.TOWER1_X, y + Const.TOWER1_Y);
		}
		if(tower2 != null) {
			tower2.setScale(Const.TOWER_FRONT_SCALE, Const.TOWER_FRONT_SCALE);
			tower2.setTranslation(x + Const.TOWER2_X, y + Const.TOWER2_Y);
		}
		if(tower3 != null) {
			tower3.setScale(Const.TOWER_BACK_SCALE, Const.TOWER_BACK_SCALE);
			tower3.setTranslation(x + Const.TOWER3_X, y + Const.TOWER3_Y);
		}
		if(tower4 != null) {
			tower4.setScale(Const.TOWER_BACK_SCALE, Const.TOWER_BACK_SCALE);
			tower4.setTranslation(x + Const.TOWER4_X, y + Const.TOWER4_Y);
		}
		if(tower_gate1 != null) {
			tower_gate1.setScale(Const.TOWER_GATE_SCALE, Const.TOWER_GATE_SCALE);
			tower_gate1.setTranslation(x + Const.TOWER_GATE1_X, y + Const.TOWER_GATE1_Y);
		}
		if(tower_gate2 != null) {
			tower_gate2.setScale(Const.TOWER_GATE_SCALE, Const.TOWER_GATE_SCALE);
			tower_gate2.setTranslation(x + Const.TOWER_GATE2_X, y + Const.TOWER_GATE2_Y);
		}
		if(wall_front != null) {
			wall_front.setScale(Const.WALL_FRONT_SCALE, Const.WALL_FRONT_SCALE_VERTICAL);
			wall_front.setTranslation(x + Const.WALL_FRONT_X, y + Const.WALL_FRONT_Y);
		}
		if(wall_back != null) {
			wall_back.setScale(Const.WALL_BACK_SCALE, Const.WALL_BACK_SCALE_VERTICAL);
			wall_back.setTranslation(x + Const.WALL_BACK_X, y + Const.WALL_BACK_Y);
		}
		if(wall_right != null) {
			wall_right.setScale(Const.WALL_SIDE_SCALE, Const.WALL_SIDE_SCALE_VERTICAL);
			wall_right.setTranslation(x + Const.WALL_RIGHT_X, y + Const.WALL_RIGHT_Y);
		}
		if(wall_left != null) {
			wall_left.setScale(Const.WALL_SIDE_SCALE, Const.WALL_SIDE_SCALE_VERTICAL);
			wall_left.setTranslation(x + Const.WALL_LEFT_X, y + Const.WALL_LEFT_Y);
		}
	}
}