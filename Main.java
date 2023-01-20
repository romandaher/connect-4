// 0: null
// 1: red
// 2: yellow
/**
 * Main.java
 * Connect 4 game with gui
 * Roman Daher
 * ICS4U
 * Period 2
 */
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
class Board {
    // handles the display and manipulation of the grid
    // Tiles: the backend data structure that tells the gui_tiles what to display
    // gui_tiles: The tiles that the user sees
    // move: the move counter
    // player: 1 = red, 2 = yellow
    static int[][] tiles = new int[7][6];
    JLabel[][] gui_tiles = new JLabel[7][6];
    JButton[] buttons = new JButton[7];
    private JFrame f = new JFrame();
    int player = 1;
    int move = 0;
    public Board() {
        // Constructor for the board
        JPanel panel = (JPanel)f.getContentPane();
        panel.setLayout(new GridLayout(7,7));
    
        for (int i = 5; i > -1; i--) {
            for (int j = 0; j < 7; j++) {
                // fills tiles with 0 to represent absence of a piece
                tiles[j][i] = 0;
                gui_tiles[j][i] = new JLabel();
                gui_tiles[j][i].setBorder(new LineBorder(Color.black));
                panel.add(gui_tiles[j][i]);
            }
        }

        for (int i = 0; i < 7; i++) {
            buttons[i] = new JButton("" + (i+1));
            buttons[i].setActionCommand(""+i);
            buttons[i].addActionListener(
                new ActionListener() {
                    // what the button does
                    public void actionPerformed(ActionEvent e) {
                        int res = add_piece(Integer.parseInt(e.getActionCommand()), player);
                        if (res != 0) {
                            // check if adding a piece failed
                            JOptionPane.showMessageDialog(null, "Column is filled");
                            return;
                        } 
                        print_board();
                        boolean is_win = check_win(player);
                        if (is_win == true) {
                            if (player == 1) {
                                JOptionPane.showMessageDialog(null, "Red wins!");
                            } else {
                                JOptionPane.showMessageDialog(null, "Yellow wins!");
                            }
                            return;
                        }
                        // flip player
                        if (player == 1) {
                            player = 2;
                        } else if (player == 2) {
                            player = 1;
                        }
                        // inc move counter, check if board is filled.
                        move++;
                        if (move == 42) {
                            JOptionPane.showMessageDialog(null, "Draw");
                        }
                    }
                }
            );
            // finish initializing buttons & board
            panel.add(buttons[i]);
        }
        f.setContentPane(panel);
        f.setSize(700,700);
        f.setVisible(true);
        f.setLocationRelativeTo(null);
    }


    public static int add_piece(int column, int colour) {
        // search for open space in column, if none, return error code 1
        for (int i = 0; i < 6; i++) {
            if (tiles[column][i] == 0) {
                tiles[column][i] = colour;
                return 0;
            } 
        }
        return 1;
    }

    public void print_board() {
        /** 
         * for playing on CLI (testing purposes)
        for (int j=5; j >= 0; j--) {
            for  (int i=0; i < 7; i++) {
                System.out.print(tiles[i][j]);
            }
            System.out.println();
        }*/
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++) {
                if (tiles[i][j] == 1) {
                    gui_tiles[i][j].setOpaque(true);
                    gui_tiles[i][j].setBackground(Color.red);
                } else if (tiles[i][j] == 2){
                    gui_tiles[i][j].setOpaque(true);
                    gui_tiles[i][j].setBackground(Color.yellow);
                }
            }
        }

    }

    public static boolean check_win(int colour) {
        // checks for a win. returns true if there is, false otherwise.

        // check vertically
        int c = 0;
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++) {
                if (tiles[i][j] == colour) {
                    c+=1;
                    if (c == 4) {
                        return true;
                    }  
                } else {
                    c = 0;
                }
            }
        }
        // check horizontally
        c = 0;
        for (int r = 0; r < 6; r++) {
            for (int j = 0; j < 7; j++) {
                if (tiles[j][r] == colour) {
                    c+=1;
                    if (c==4) {
                        return true;
                    }
                } else {
                    c = 0;
                }
            }
        }
        // check ascending diagonal
        c = 0;
        for (int i=0; i < 3; i++) {
            for (int j = 0; j < 7; j++) {
                if (i+j > 5) {
                    c = 0;
                    break;
                }
                if (tiles[j][i+j] == colour) {
                    c+=1;
                    if (c==4) {
                        return true;
                    }
                } else {
                    c = 0;
                }
            }
        }
        c = 0;
        for (int i = 1; i < 4;i++) {
            for (int j = 0; j < 6; j++) {
                if (i+j > 6) {
                    c = 0;
                    break;
                }
                if (tiles[j+i][j] == colour) {
                    c+=1;
                    if (c==4) {
                        return true;
                    }
                } else {
                    c = 0;
                }
            }
        }
        // check descending diagonal
        c = 0;
        for (int i = 5; i > 2; i--) {
            for (int j = 0; j < 7; j++) {
                if (i-j < 0) {
                    c = 0; 
                    break;
                }
                if (tiles[j][i-j] == colour) {
                    c+=1;
                    if (c==4) {
                        return true;
                    }
                } else {
                    c = 0;
                }
            }
        }
        c = 0;
        int a = 0;
        for (int i=1; i < 4; i++) {
            for (int j = 5; j > -1; j--) {
                if (a+i > 6) {
                    a = 0;
                    c = 0;
                    break;
                }
                if (tiles[a+i][j] == colour) {
                    c+=1;
                    if (c==4) {
                        return true;
                    }
                } else {
                    c = 0;
                }
                a++;
            }
        }
        return false;
    }
}

class Main {
    public static void main(String[] args) {
        // init board, start gui.
        Board game = new Board();
        game.print_board();

    }
}
