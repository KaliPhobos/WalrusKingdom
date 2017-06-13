package WalrusKingdom;
public class Block {
	// The Array BLOCKS defines all Information on the different Blocks / Tiles used in the Game
	// Each Block only provides ONE picture-id since Foreground and Background will be two separated Layers of Blocks
	// That way Triggers can get attached  to / affect background and foreground separately
	public static boolean isUnknownBlockSolid = true;
	long id;
	String name;
	boolean isSolid;
	int PictureID;
	int TriggerID;
	public static Block[] Blocks = new Block[200];
	public Block(int _id, String _name, boolean _isSolid, int _PictureID, int _TriggerID) {
		this.id = _id;					// used in the matrix-map
		this.name = _name;				// just for fun
		this.isSolid = _isSolid;		// to check if walking through this is possible
		this.PictureID = _PictureID;	// picture link (maybe later replace this with a bitmap object or something)
		this.TriggerID = _TriggerID;	// give a number to every special event that is triggered on walk-overs.
										// So I can check for these IDs instead of having to check for hard-coded coordinates
										// Only for events that are always triggered by such a block, live Saving-points.
	}
	public static int getPictureIDfromID(long _id) {
		int _pos = getPosFromID(_id);
		int _PictureID = 0;
		try {
			Block _block = Blocks[_pos];
			_PictureID = _block.PictureID;
		}
		catch(NullPointerException e) {
			// No such block
		}
		catch(Exception e) {
			System.out.println("komischer Scheiﬂ");
		}
		return _PictureID;
	}
	public static int getPictureIDfromPos(int _pos) {
		int _PictureID = 0;
		try {
			Block _block = Blocks[_pos];
			_PictureID = _block.PictureID;
		}
		catch(NullPointerException e) {
			// No such block
		}
		catch(Exception e) {
			System.out.println("komischer Scheiﬂ");
		}
		return _PictureID;
	}
	public int getPictureID() {
		return this.PictureID;
	}
	public static int getTriggerIDFromID(long _id) {
		int _pos = getPosFromID(_id);
		int _TriggerID = 0;
		try {
			Block _block = Blocks[_pos];
			_TriggerID = _block.TriggerID;
		}
		catch(NullPointerException e) {
			// No such block
		}
		catch(Exception e) {
			System.out.println("komischer Scheiﬂ");
		}
		return _TriggerID;
	}
	public static int getTriggerIDFromPos(int _pos) {
		int _TriggerID = 0;
		try {
			Block _block = Blocks[_pos];
			_TriggerID = _block.TriggerID;
		}
		catch(NullPointerException e) {
			// No such block
		}
		catch(Exception e) {
			System.out.println("komischer Scheiﬂ");
		}
		return _TriggerID;
	}
	public int getTriggerID() {
		return this.TriggerID;
	}
	public static int getPosFromID(long _id) {
		int _pos = -1;
		Exitloop:
		for(int i_FindBlock=0; i_FindBlock<Blocks.length; i_FindBlock++) {
			Block _block = Blocks[i_FindBlock];
			if(_block!=null && _block.id == _id) {
				_pos = i_FindBlock;
				break Exitloop;
			}
		}
		return _pos;
	}
	public static long getIDFromPos(int _pos) {
		Block _block = Blocks[_pos];
		if(_block!=null) {
			return _block.id;
		} else {
			return -1;
		}
	}
	public static void AddNew(Block NewBlock) {
        for(int i_AddNew=0; i_AddNew<Blocks.length; i_AddNew++) {
            if(getNameFromPos(i_AddNew).equals("")) {
                Blocks[i_AddNew] = NewBlock;
                System.out.println("Block created: ∞"+i_AddNew+"       id="+NewBlock.id+"      name="+NewBlock.name+"      isSolid="+NewBlock.isSolid+"        PictureID="+NewBlock.PictureID+"      TriggerID="+NewBlock.TriggerID);
                break;
            }
        }
    }
    public static void AddNew(int _id, String _name, boolean _isSolid, int _PictureID, int _TriggerID) {
        for(int i_AddNew=0; i_AddNew<Blocks.length; i_AddNew++) {
            if(getNameFromPos(i_AddNew).equals("")) {
                Blocks[i_AddNew] = new Block(_id, _name, _isSolid, _PictureID, _TriggerID);
                System.out.println("Block created: ∞"+i_AddNew+"       id="+_id+"      name="+_name+"      isSolid="+_isSolid+"        PictureID="+_PictureID+"      TriggerID="+_TriggerID);
                break;
            }
        }
    }
	public static boolean getSolidFromID(long _id) {
		int _pos = getPosFromID(_id);
		return getSolidFromPos(_pos);
	}
	public static boolean getSolidFromPos(int _pos) {
        if(_pos==-1) {
            return isUnknownBlockSolid;
        } else if(Blocks[_pos]==null) {
            return isUnknownBlockSolid;
        } else {
            return Blocks[_pos].isSolid;
        }
	}
	public boolean getSolid() {
		return this.isSolid;
	}
    public static String getNameFromID(long _id) {
        int _pos = getPosFromID(_id);
        if(_pos==-1) {
            return "";
        } else if(Blocks[_pos]==null) {
            return "";
        } else {
            return Blocks[_pos].name;
        }
    }
    public static String getNameFromPos(int _pos) {
        if(_pos==-1) {
            return "";
        } else if(Blocks[_pos]==null) {
            return "";
        } else {
            return Blocks[_pos].name;
        }
    }
	public String getName() {
		return this.name;
	}


}