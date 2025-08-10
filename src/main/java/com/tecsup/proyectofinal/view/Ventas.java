/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.tecsup.proyectofinal.view;

import com.tecsup.proyectofinal.controller.VentaController;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author bob_s
 */
public class Ventas extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Ventas.class.getName());
    private VentaController controller;

    /**
     * Creates new form Ventas
     */
    public Ventas() {
        initComponents();
        
        this.controller = new VentaController(this);
        setLocationRelativeTo(null); // centra la ventana en la pantalla
 
    }
    
    public void mostrarMensaje(String mensaje, String titulo, int tipoMensaje) {
        JOptionPane.showMessageDialog(this, mensaje, titulo, tipoMensaje);
    }

    public JButton getAgregarProducto() {
        return AgregarProducto;
    }

    public void setAgregarProducto(JButton AgregarProducto) {
        this.AgregarProducto = AgregarProducto;
    }

    public JTextField getApellidoClienteVenta() {
        return ApellidoClienteVenta;
    }

    public void setApellidoClienteVenta(JTextField ApellidoClienteVenta) {
        this.ApellidoClienteVenta = ApellidoClienteVenta;
    }

    public JTextField getBuscarClienteVenta() {
        return BuscarClienteVenta;
    }

    public void setBuscarClienteVenta(JTextField BuscarClienteVenta) {
        this.BuscarClienteVenta = BuscarClienteVenta;
    }

    public JTextField getBuscarProductoVenta() {
        return BuscarProductoVenta;
    }

    public void setBuscarProductoVenta(JTextField BuscarProductoVenta) {
        this.BuscarProductoVenta = BuscarProductoVenta;
    }

    public JTextField getCantidadProductoVenta() {
        return CantidadProductoVenta;
    }

    public void setCantidadProductoVenta(JTextField CantidadProductoVenta) {
        this.CantidadProductoVenta = CantidadProductoVenta;
    }

    public JTextField getCodClienteVenta() {
        return CodClienteVenta;
    }

    public void setCodClienteVenta(JTextField CodClienteVenta) {
        this.CodClienteVenta = CodClienteVenta;
    }

    public JTextField getCodProductoVenta() {
        return CodProductoVenta;
    }

    public void setCodProductoVenta(JTextField CodProductoVenta) {
        this.CodProductoVenta = CodProductoVenta;
    }

    public JTextField getDescripcionProductoVenta() {
        return DescripcionProductoVenta;
    }

    public void setDescripcionProductoVenta(JTextField DescripcionProductoVenta) {
        this.DescripcionProductoVenta = DescripcionProductoVenta;
    }

    public JTextField getDireccionClienteVenta() {
        return DireccionClienteVenta;
    }

    public void setDireccionClienteVenta(JTextField DireccionClienteVenta) {
        this.DireccionClienteVenta = DireccionClienteVenta;
    }

    public JButton getEliminarProductoVenta() {
        return EliminarProductoVenta;
    }

    public void setEliminarProductoVenta(JButton EliminarProductoVenta) {
        this.EliminarProductoVenta = EliminarProductoVenta;
    }

    public JTextField getFechaVenta() {
        return FechaVenta;
    }

    public void setFechaVenta(JTextField FechaVenta) {
        this.FechaVenta = FechaVenta;
    }

    public JButton getHacerFacturaVenta() {
        return HacerFacturaVenta;
    }

    public void setHacerFacturaVenta(JButton HacerFacturaVenta) {
        this.HacerFacturaVenta = HacerFacturaVenta;
    }

    public JTextField getIgvVenta() {
        return IgvVenta;
    }

    public void setIgvVenta(JTextField IgvVenta) {
        this.IgvVenta = IgvVenta;
    }

    public JTextField getNombreClienteVenta() {
        return NombreClienteVenta;
    }

    public void setNombreClienteVenta(JTextField NombreClienteVenta) {
        this.NombreClienteVenta = NombreClienteVenta;
    }

    public JTextField getNumFactura() {
        return NumFactura;
    }

    public void setNumFactura(JTextField NumFactura) {
        this.NumFactura = NumFactura;
    }

    public JTextField getPrecioProductoVenta() {
        return PrecioProductoVenta;
    }

    public void setPrecioProductoVenta(JTextField PrecioProductoVenta) {
        this.PrecioProductoVenta = PrecioProductoVenta;
    }

    public JTextField getStockProductoVenta() {
        return StockProductoVenta;
    }

    public void setStockProductoVenta(JTextField StockProductoVenta) {
        this.StockProductoVenta = StockProductoVenta;
    }

    public JTextField getSubtotalVenta() {
        return SubtotalVenta;
    }

    public void setSubtotalVenta(JTextField SubtotalVenta) {
        this.SubtotalVenta = SubtotalVenta;
    }

    public JTable getTablaFacturaVenta() {
        return TablaFacturaVenta;
    }

    public void setTablaFacturaVenta(JTable TablaFacturaVenta) {
        this.TablaFacturaVenta = TablaFacturaVenta;
    }

    public JTable getTablaProductoVenta() {
        return TablaProductoVenta;
    }

    public void setTablaProductoVenta(JTable TablaProductoVenta) {
        this.TablaProductoVenta = TablaProductoVenta;
    }

    public JTextField getTelefonoClienteVenta() {
        return TelefonoClienteVenta;
    }

    public void setTelefonoClienteVenta(JTextField TelefonoClienteVenta) {
        this.TelefonoClienteVenta = TelefonoClienteVenta;
    }

    public JTextField getTotalVenta() {
        return TotalVenta;
    }

    public void setTotalVenta(JTextField TotalVenta) {
        this.TotalVenta = TotalVenta;
    }

    public JTable getTablaClienteVenta() {
        return TablaClienteVenta;
    }

    public void setTablaClienteVenta(JTable TablaClienteVenta) {
        this.TablaClienteVenta = TablaClienteVenta;
    }
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelNavegacion = new javax.swing.JPanel();
        GestionProductosButton = new javax.swing.JButton();
        ReportesButton = new javax.swing.JButton();
        ClientesButton = new javax.swing.JButton();
        SalirButton = new javax.swing.JButton();
        VentasButton = new javax.swing.JButton();
        LogoButton = new javax.swing.JButton();
        PanelClientesDisponibles = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        BuscarClienteVenta = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        CodClienteVenta = new javax.swing.JTextField();
        NombreClienteVenta = new javax.swing.JTextField();
        ApellidoClienteVenta = new javax.swing.JTextField();
        DireccionClienteVenta = new javax.swing.JTextField();
        TelefonoClienteVenta = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        TablaClienteVenta = new javax.swing.JTable();
        PanelProductoVenta = new javax.swing.JPanel();
        PrecioProductoVenta = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TablaProductoVenta = new javax.swing.JTable();
        jLabel18 = new javax.swing.JLabel();
        BuscarProductoVenta = new javax.swing.JTextField();
        CantidadProductoVenta = new javax.swing.JTextField();
        CodProductoVenta = new javax.swing.JTextField();
        StockProductoVenta = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        DescripcionProductoVenta = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        AgregarProducto = new javax.swing.JButton();
        jPanelFacturacion = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        TablaFacturaVenta = new javax.swing.JTable();
        jLabel21 = new javax.swing.JLabel();
        EliminarProductoVenta = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        IgvVenta = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        TotalVenta = new javax.swing.JTextField();
        HacerFacturaVenta = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        FechaVenta = new javax.swing.JTextField();
        NumFactura = new javax.swing.JTextField();
        SubtotalVenta = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setPreferredSize(new java.awt.Dimension(1440, 900));
        setSize(new java.awt.Dimension(0, 0));
        getContentPane().setLayout(null);

        PanelNavegacion.setBackground(new java.awt.Color(0, 118, 168));
        PanelNavegacion.setPreferredSize(new java.awt.Dimension(339, 900));

        GestionProductosButton.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        GestionProductosButton.setForeground(new java.awt.Color(255, 255, 255));
        GestionProductosButton.setText("Gestion de Productos");
        GestionProductosButton.setBorder(null);
        GestionProductosButton.setBorderPainted(false);
        GestionProductosButton.setContentAreaFilled(false);
        GestionProductosButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        GestionProductosButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GestionProductosButtonActionPerformed(evt);
            }
        });

        ReportesButton.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        ReportesButton.setForeground(new java.awt.Color(255, 255, 255));
        ReportesButton.setText("Reportes");
        ReportesButton.setBorder(null);
        ReportesButton.setBorderPainted(false);
        ReportesButton.setContentAreaFilled(false);
        ReportesButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ReportesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReportesButtonActionPerformed(evt);
            }
        });

        ClientesButton.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        ClientesButton.setForeground(new java.awt.Color(255, 255, 255));
        ClientesButton.setText("Clientes");
        ClientesButton.setBorder(null);
        ClientesButton.setBorderPainted(false);
        ClientesButton.setContentAreaFilled(false);
        ClientesButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ClientesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClientesButtonActionPerformed(evt);
            }
        });

        SalirButton.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        SalirButton.setForeground(new java.awt.Color(255, 255, 255));
        SalirButton.setText("Salir");
        SalirButton.setBorder(null);
        SalirButton.setBorderPainted(false);
        SalirButton.setContentAreaFilled(false);
        SalirButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        SalirButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalirButtonActionPerformed(evt);
            }
        });

        VentasButton.setBackground(new java.awt.Color(0, 67, 96));
        VentasButton.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        VentasButton.setForeground(new java.awt.Color(255, 255, 255));
        VentasButton.setText("Ventas                                ");
        VentasButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        VentasButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        VentasButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VentasButtonActionPerformed(evt);
            }
        });

        LogoButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/LogoSecciones.png"))); // NOI18N
        LogoButton.setBorder(null);
        LogoButton.setBorderPainted(false);
        LogoButton.setContentAreaFilled(false);
        LogoButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        LogoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogoButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelNavegacionLayout = new javax.swing.GroupLayout(PanelNavegacion);
        PanelNavegacion.setLayout(PanelNavegacionLayout);
        PanelNavegacionLayout.setHorizontalGroup(
            PanelNavegacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelNavegacionLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(PanelNavegacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(LogoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PanelNavegacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(GestionProductosButton)
                        .addComponent(ReportesButton)
                        .addComponent(ClientesButton)
                        .addComponent(SalirButton)))
                .addGap(42, 49, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelNavegacionLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(VentasButton, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        PanelNavegacionLayout.setVerticalGroup(
            PanelNavegacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelNavegacionLayout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(LogoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80)
                .addComponent(GestionProductosButton)
                .addGap(41, 41, 41)
                .addComponent(VentasButton, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(ReportesButton)
                .addGap(47, 47, 47)
                .addComponent(ClientesButton)
                .addGap(117, 117, 117)
                .addComponent(SalirButton)
                .addContainerGap(153, Short.MAX_VALUE))
        );

        getContentPane().add(PanelNavegacion);
        PanelNavegacion.setBounds(0, 0, 340, 900);

        PanelClientesDisponibles.setBackground(new java.awt.Color(255, 239, 232));

        jLabel2.setFont(new java.awt.Font("Roboto", 1, 22)); // NOI18N
        jLabel2.setText("Clientes");

        jLabel3.setFont(new java.awt.Font("Roboto", 1, 15)); // NOI18N
        jLabel3.setText("Buscador:");

        BuscarClienteVenta.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        BuscarClienteVenta.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        BuscarClienteVenta.setPreferredSize(new java.awt.Dimension(380, 27));
        BuscarClienteVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscarClienteVentaActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel4.setText("Selecciona Cliente");

        jLabel5.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel5.setText("Datos del Cliente");
        jLabel5.setPreferredSize(new java.awt.Dimension(143, 20));

        jLabel6.setFont(new java.awt.Font("Roboto", 1, 15)); // NOI18N
        jLabel6.setText("Nombre:");

        jLabel7.setFont(new java.awt.Font("Roboto", 1, 15)); // NOI18N
        jLabel7.setText("CodCliente:");

        jLabel8.setFont(new java.awt.Font("Roboto", 1, 15)); // NOI18N
        jLabel8.setText("Apellido:");

        jLabel9.setFont(new java.awt.Font("Roboto", 1, 15)); // NOI18N
        jLabel9.setText("Teléfono:");

        jLabel10.setFont(new java.awt.Font("Roboto", 1, 15)); // NOI18N
        jLabel10.setText("Dirección:");

        CodClienteVenta.setFont(new java.awt.Font("sansserif", 0, 13)); // NOI18N
        CodClienteVenta.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        CodClienteVenta.setEnabled(false);
        CodClienteVenta.setMinimumSize(new java.awt.Dimension(64, 25));
        CodClienteVenta.setPreferredSize(new java.awt.Dimension(74, 25));
        CodClienteVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CodClienteVentaActionPerformed(evt);
            }
        });

        NombreClienteVenta.setFont(new java.awt.Font("sansserif", 0, 13)); // NOI18N
        NombreClienteVenta.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        NombreClienteVenta.setEnabled(false);
        NombreClienteVenta.setPreferredSize(new java.awt.Dimension(74, 25));

        ApellidoClienteVenta.setFont(new java.awt.Font("sansserif", 0, 13)); // NOI18N
        ApellidoClienteVenta.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        ApellidoClienteVenta.setEnabled(false);
        ApellidoClienteVenta.setPreferredSize(new java.awt.Dimension(74, 25));

        DireccionClienteVenta.setFont(new java.awt.Font("sansserif", 0, 13)); // NOI18N
        DireccionClienteVenta.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        DireccionClienteVenta.setEnabled(false);
        DireccionClienteVenta.setPreferredSize(new java.awt.Dimension(74, 25));
        DireccionClienteVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DireccionClienteVentaActionPerformed(evt);
            }
        });

        TelefonoClienteVenta.setFont(new java.awt.Font("sansserif", 0, 13)); // NOI18N
        TelefonoClienteVenta.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        TelefonoClienteVenta.setEnabled(false);
        TelefonoClienteVenta.setFocusable(false);
        TelefonoClienteVenta.setPreferredSize(new java.awt.Dimension(74, 25));

        TablaClienteVenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "codCliente", "DNI", "Apellido", "Nombre"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TablaClienteVenta.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(TablaClienteVenta);
        TablaClienteVenta.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        javax.swing.GroupLayout PanelClientesDisponiblesLayout = new javax.swing.GroupLayout(PanelClientesDisponibles);
        PanelClientesDisponibles.setLayout(PanelClientesDisponiblesLayout);
        PanelClientesDisponiblesLayout.setHorizontalGroup(
            PanelClientesDisponiblesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelClientesDisponiblesLayout.createSequentialGroup()
                .addGap(213, 213, 213)
                .addComponent(jLabel2)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(PanelClientesDisponiblesLayout.createSequentialGroup()
                .addGroup(PanelClientesDisponiblesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelClientesDisponiblesLayout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BuscarClienteVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelClientesDisponiblesLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(PanelClientesDisponiblesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PanelClientesDisponiblesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(DireccionClienteVenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ApellidoClienteVenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(NombreClienteVenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(CodClienteVenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(TelefonoClienteVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(PanelClientesDisponiblesLayout.createSequentialGroup()
                        .addGap(410, 410, 410)
                        .addComponent(jLabel4))
                    .addGroup(PanelClientesDisponiblesLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(PanelClientesDisponiblesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 502, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        PanelClientesDisponiblesLayout.setVerticalGroup(
            PanelClientesDisponiblesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelClientesDisponiblesLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelClientesDisponiblesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BuscarClienteVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelClientesDisponiblesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(CodClienteVenta, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelClientesDisponiblesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NombreClienteVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelClientesDisponiblesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ApellidoClienteVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelClientesDisponiblesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DireccionClienteVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelClientesDisponiblesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TelefonoClienteVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(PanelClientesDisponibles);
        PanelClientesDisponibles.setBounds(330, 0, 560, 423);

        PanelProductoVenta.setBackground(new java.awt.Color(255, 239, 232));

        PrecioProductoVenta.setFont(new java.awt.Font("sansserif", 0, 13)); // NOI18N
        PrecioProductoVenta.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        PrecioProductoVenta.setEnabled(false);
        PrecioProductoVenta.setFocusable(false);
        PrecioProductoVenta.setPreferredSize(new java.awt.Dimension(74, 25));

        jLabel17.setFont(new java.awt.Font("Roboto", 1, 15)); // NOI18N
        jLabel17.setText("Buscador:");

        TablaProductoVenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "codProducto", "Descripción", "Precio", "Stock"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TablaProductoVenta.setColumnSelectionAllowed(true);
        TablaProductoVenta.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(TablaProductoVenta);
        TablaProductoVenta.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        jLabel18.setFont(new java.awt.Font("Roboto", 1, 15)); // NOI18N
        jLabel18.setText("Cantidad:");

        BuscarProductoVenta.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        BuscarProductoVenta.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        BuscarProductoVenta.setPreferredSize(new java.awt.Dimension(380, 27));

        CantidadProductoVenta.setFont(new java.awt.Font("sansserif", 0, 13)); // NOI18N
        CantidadProductoVenta.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        CantidadProductoVenta.setPreferredSize(new java.awt.Dimension(74, 25));
        CantidadProductoVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CantidadProductoVentaActionPerformed(evt);
            }
        });

        CodProductoVenta.setFont(new java.awt.Font("sansserif", 0, 13)); // NOI18N
        CodProductoVenta.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        CodProductoVenta.setEnabled(false);
        CodProductoVenta.setFocusable(false);
        CodProductoVenta.setMinimumSize(new java.awt.Dimension(64, 25));
        CodProductoVenta.setPreferredSize(new java.awt.Dimension(74, 25));
        CodProductoVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CodProductoVentaActionPerformed(evt);
            }
        });

        StockProductoVenta.setFont(new java.awt.Font("sansserif", 0, 13)); // NOI18N
        StockProductoVenta.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        StockProductoVenta.setEnabled(false);
        StockProductoVenta.setFocusable(false);
        StockProductoVenta.setPreferredSize(new java.awt.Dimension(74, 25));

        jLabel19.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel19.setText("Selecciona Producto");

        jLabel11.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel11.setText("Datos del Producto");
        jLabel11.setPreferredSize(new java.awt.Dimension(143, 20));

        DescripcionProductoVenta.setFont(new java.awt.Font("sansserif", 0, 13)); // NOI18N
        DescripcionProductoVenta.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        DescripcionProductoVenta.setEnabled(false);
        DescripcionProductoVenta.setFocusable(false);
        DescripcionProductoVenta.setPreferredSize(new java.awt.Dimension(74, 25));

        jLabel12.setFont(new java.awt.Font("Roboto", 1, 15)); // NOI18N
        jLabel12.setText("Descripcion:");

        jLabel13.setFont(new java.awt.Font("Roboto", 1, 15)); // NOI18N
        jLabel13.setText("CodProducto:");

        jLabel14.setFont(new java.awt.Font("Roboto", 1, 15)); // NOI18N
        jLabel14.setText("Precio:");

        jLabel15.setFont(new java.awt.Font("Roboto", 1, 22)); // NOI18N
        jLabel15.setText("Producto");

        jLabel16.setFont(new java.awt.Font("Roboto", 1, 15)); // NOI18N
        jLabel16.setText("Stock:");

        AgregarProducto.setBackground(new java.awt.Color(0, 118, 168));
        AgregarProducto.setFont(new java.awt.Font("Roboto", 1, 15)); // NOI18N
        AgregarProducto.setForeground(new java.awt.Color(255, 255, 255));
        AgregarProducto.setText("Agregar Producto");
        AgregarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AgregarProductoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelProductoVentaLayout = new javax.swing.GroupLayout(PanelProductoVenta);
        PanelProductoVenta.setLayout(PanelProductoVentaLayout);
        PanelProductoVentaLayout.setHorizontalGroup(
            PanelProductoVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelProductoVentaLayout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addGroup(PanelProductoVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelProductoVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(PanelProductoVentaLayout.createSequentialGroup()
                            .addGap(21, 21, 21)
                            .addGroup(PanelProductoVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel13)
                                .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(PanelProductoVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(PanelProductoVentaLayout.createSequentialGroup()
                                    .addComponent(CantidadProductoVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(37, 37, 37)
                                    .addComponent(jLabel16)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(StockProductoVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(DescripcionProductoVenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(CodProductoVenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(PrecioProductoVenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(PanelProductoVentaLayout.createSequentialGroup()
                            .addGroup(PanelProductoVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 502, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(PanelProductoVentaLayout.createSequentialGroup()
                                    .addGap(200, 200, 200)
                                    .addComponent(jLabel15))
                                .addGroup(PanelProductoVentaLayout.createSequentialGroup()
                                    .addGap(12, 12, 12)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(29, 35, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelProductoVentaLayout.createSequentialGroup()
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addGroup(PanelProductoVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel19)
                                .addGroup(PanelProductoVentaLayout.createSequentialGroup()
                                    .addComponent(jLabel17)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(BuscarProductoVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(39, 39, 39)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelProductoVentaLayout.createSequentialGroup()
                        .addComponent(AgregarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(164, 164, 164))))
        );
        PanelProductoVentaLayout.setVerticalGroup(
            PanelProductoVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelProductoVentaLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelProductoVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BuscarProductoVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelProductoVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(CodProductoVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelProductoVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DescripcionProductoVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelProductoVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PrecioProductoVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelProductoVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CantidadProductoVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(StockProductoVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(AgregarProducto)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(PanelProductoVenta);
        PanelProductoVenta.setBounds(887, 0, 550, 420);

        jPanelFacturacion.setBackground(new java.awt.Color(255, 239, 232));
        jPanelFacturacion.setEnabled(false);
        jPanelFacturacion.setFocusable(false);
        jPanelFacturacion.setLayout(null);

        jLabel20.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel20.setText("Resumen de venta");
        jLabel20.setPreferredSize(new java.awt.Dimension(143, 20));
        jPanelFacturacion.add(jLabel20);
        jLabel20.setBounds(20, 10, 180, 20);

        TablaFacturaVenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "CodProducto", "Descripción", "PrecioUnitario", "Cantidad", "Subtotal"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Integer.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TablaFacturaVenta.setColumnSelectionAllowed(true);
        TablaFacturaVenta.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(TablaFacturaVenta);
        TablaFacturaVenta.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        jPanelFacturacion.add(jScrollPane3);
        jScrollPane3.setBounds(15, 33, 1050, 300);

        jLabel21.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel21.setText("Seleccionar para:");
        jPanelFacturacion.add(jLabel21);
        jLabel21.setBounds(20, 340, 93, 16);

        EliminarProductoVenta.setBackground(new java.awt.Color(255, 0, 0));
        EliminarProductoVenta.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        EliminarProductoVenta.setForeground(new java.awt.Color(255, 255, 255));
        EliminarProductoVenta.setText("Eliminar Producto");
        EliminarProductoVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarProductoVentaActionPerformed(evt);
            }
        });
        jPanelFacturacion.add(EliminarProductoVenta);
        EliminarProductoVenta.setBounds(20, 360, 191, 30);

        jLabel22.setFont(new java.awt.Font("Roboto", 1, 15)); // NOI18N
        jLabel22.setText("IGV (18%):");
        jPanelFacturacion.add(jLabel22);
        jLabel22.setBounds(810, 390, 80, 27);

        jLabel23.setFont(new java.awt.Font("Roboto", 1, 15)); // NOI18N
        jLabel23.setText("TOTAL:");
        jPanelFacturacion.add(jLabel23);
        jLabel23.setBounds(820, 420, 70, 21);

        IgvVenta.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        IgvVenta.setEnabled(false);
        IgvVenta.setFocusable(false);
        IgvVenta.setPreferredSize(new java.awt.Dimension(64, 21));
        jPanelFacturacion.add(IgvVenta);
        IgvVenta.setBounds(890, 390, 171, 21);

        jTextField2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanelFacturacion.add(jTextField2);
        jTextField2.setBounds(909, 395, 171, 0);

        TotalVenta.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        TotalVenta.setEnabled(false);
        TotalVenta.setFocusable(false);
        TotalVenta.setPreferredSize(new java.awt.Dimension(64, 21));
        jPanelFacturacion.add(TotalVenta);
        TotalVenta.setBounds(890, 420, 171, 21);

        HacerFacturaVenta.setBackground(new java.awt.Color(0, 118, 168));
        HacerFacturaVenta.setFont(new java.awt.Font("Roboto", 1, 19)); // NOI18N
        HacerFacturaVenta.setForeground(new java.awt.Color(255, 255, 255));
        HacerFacturaVenta.setText("Cobrar");
        HacerFacturaVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HacerFacturaVentaActionPerformed(evt);
            }
        });
        jPanelFacturacion.add(HacerFacturaVenta);
        HacerFacturaVenta.setBounds(300, 390, 442, 36);

        jLabel24.setFont(new java.awt.Font("Roboto", 1, 15)); // NOI18N
        jLabel24.setText("Fecha de Venta");
        jPanelFacturacion.add(jLabel24);
        jLabel24.setBounds(438, 7, 120, 20);

        jLabel25.setFont(new java.awt.Font("Roboto", 1, 15)); // NOI18N
        jLabel25.setText("Nº de Factura:");
        jPanelFacturacion.add(jLabel25);
        jLabel25.setBounds(800, 10, 120, 20);

        FechaVenta.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        FechaVenta.setEnabled(false);
        FechaVenta.setFocusable(false);
        jPanelFacturacion.add(FechaVenta);
        FechaVenta.setBounds(570, 10, 147, 20);

        NumFactura.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        NumFactura.setEnabled(false);
        NumFactura.setFocusable(false);
        NumFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NumFacturaActionPerformed(evt);
            }
        });
        jPanelFacturacion.add(NumFactura);
        NumFactura.setBounds(933, 8, 130, 20);

        SubtotalVenta.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        SubtotalVenta.setEnabled(false);
        SubtotalVenta.setFocusable(false);
        SubtotalVenta.setPreferredSize(new java.awt.Dimension(64, 21));
        jPanelFacturacion.add(SubtotalVenta);
        SubtotalVenta.setBounds(890, 360, 171, 21);

        jLabel26.setFont(new java.awt.Font("Roboto", 1, 15)); // NOI18N
        jLabel26.setText("SUBTOTAL:");
        jPanelFacturacion.add(jLabel26);
        jLabel26.setBounds(790, 360, 90, 27);

        getContentPane().add(jPanelFacturacion);
        jPanelFacturacion.setBounds(340, 420, 1100, 480);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void GestionProductosButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GestionProductosButtonActionPerformed
        GestionProductos gp = new GestionProductos();  // Crear instancia del formulario
        gp.setVisible(true);                           // Mostrar el formulario
        this.dispose();                                // Cerrar el formulario actual
    }//GEN-LAST:event_GestionProductosButtonActionPerformed

    private void ReportesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReportesButtonActionPerformed
        Reportes reportes = new Reportes();  // Crear instancia del formulario
        reportes.setVisible(true);                           // Mostrar el formulario
        this.dispose();                                // Cerrar el formulario actual
    }//GEN-LAST:event_ReportesButtonActionPerformed

    private void ClientesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClientesButtonActionPerformed
        Clientes clientes = new Clientes();  // Crear instancia del formulario
        clientes.setVisible(true);                           // Mostrar el formulario
        this.dispose();                                // Cerrar el formulario actual
    }//GEN-LAST:event_ClientesButtonActionPerformed

    private void SalirButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalirButtonActionPerformed
        Login login = new Login();this.dispose();
        login.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_SalirButtonActionPerformed

    private void DireccionClienteVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DireccionClienteVentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DireccionClienteVentaActionPerformed

    private void CodClienteVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CodClienteVentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CodClienteVentaActionPerformed

    private void BuscarClienteVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarClienteVentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BuscarClienteVentaActionPerformed

    private void AgregarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AgregarProductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AgregarProductoActionPerformed

    private void CodProductoVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CodProductoVentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CodProductoVentaActionPerformed

    private void CantidadProductoVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CantidadProductoVentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CantidadProductoVentaActionPerformed

    private void NumFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NumFacturaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NumFacturaActionPerformed

    private void HacerFacturaVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HacerFacturaVentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_HacerFacturaVentaActionPerformed

    private void EliminarProductoVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarProductoVentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EliminarProductoVentaActionPerformed

    private void VentasButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VentasButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_VentasButtonActionPerformed

    private void LogoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogoButtonActionPerformed
        // TODO add your handling code here:
        Dashboard dashboard = new Dashboard();  // Crear instancia del formulario
        dashboard.setVisible(true);                           // Mostrar el formulario
        this.dispose();
    }//GEN-LAST:event_LogoButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new Ventas().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AgregarProducto;
    private javax.swing.JTextField ApellidoClienteVenta;
    private javax.swing.JTextField BuscarClienteVenta;
    private javax.swing.JTextField BuscarProductoVenta;
    private javax.swing.JTextField CantidadProductoVenta;
    private javax.swing.JButton ClientesButton;
    private javax.swing.JTextField CodClienteVenta;
    private javax.swing.JTextField CodProductoVenta;
    private javax.swing.JTextField DescripcionProductoVenta;
    private javax.swing.JTextField DireccionClienteVenta;
    private javax.swing.JButton EliminarProductoVenta;
    private javax.swing.JTextField FechaVenta;
    private javax.swing.JButton GestionProductosButton;
    private javax.swing.JButton HacerFacturaVenta;
    private javax.swing.JTextField IgvVenta;
    private javax.swing.JButton LogoButton;
    private javax.swing.JTextField NombreClienteVenta;
    private javax.swing.JTextField NumFactura;
    private javax.swing.JPanel PanelClientesDisponibles;
    private javax.swing.JPanel PanelNavegacion;
    private javax.swing.JPanel PanelProductoVenta;
    private javax.swing.JTextField PrecioProductoVenta;
    private javax.swing.JButton ReportesButton;
    private javax.swing.JButton SalirButton;
    private javax.swing.JTextField StockProductoVenta;
    private javax.swing.JTextField SubtotalVenta;
    private javax.swing.JTable TablaClienteVenta;
    private javax.swing.JTable TablaFacturaVenta;
    private javax.swing.JTable TablaProductoVenta;
    private javax.swing.JTextField TelefonoClienteVenta;
    private javax.swing.JTextField TotalVenta;
    private javax.swing.JButton VentasButton;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanelFacturacion;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
