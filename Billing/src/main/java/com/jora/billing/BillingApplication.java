package com.jora.billing;

import java.awt.Color;
import java.security.Security;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ComponentScan;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.jora.billing.connection.ApplicationConfig;
import com.jora.billing.form.FrmLogging;
import com.jora.billing.form.FrmMdi;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@ComponentScan(basePackages = { "com.jora.billing.*" })
public class BillingApplication implements ApplicationContextAware {
	public static ApplicationContext applicationContext;

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		try {
			/*** For Disable (TLSv1, TLSv1.1) Algorithms ***/

			String disabledAlgorithms = Security.getProperty("jdk.tls.disabledAlgorithms");
			String[] algorithms = disabledAlgorithms.split(",");
			for (int i = 0; i < algorithms.length; i++) {
				String string = algorithms[i];
				if ("TLSv1".contains(string.trim()) || "TLSv1.1".contains(string.trim())) {
					algorithms[i] = null;
				}
			}
			String altered = "";
			for (int i = 0; i < algorithms.length; i++) {
				String string = algorithms[i];
				if (null == string) {
					continue;
				}

				altered += string + ",";
			}
			Security.setProperty("jdk.tls.disabledAlgorithms", altered);
			UIManager.setLookAndFeel(new FlatIntelliJLaf());
			UIManager.put("Button.focusedBackground", Color.BLUE);
			UIManager.put("Button.focusedForeground", Color.WHITE);

			UIManager.put("Button.hoverBackground", Color.BLUE);
			UIManager.put("Button.hoverForeground", Color.WHITE);

			ApplicationConfig.fileRead();

			new SpringApplicationBuilder(BillingApplication.class).headless(false).run(args);

			FrmMdi frmMdi = applicationContext.getBean(FrmMdi.class);
			frmMdi.setVisible(true);

//			FrmLogging frmLogging = applicationContext.getBean(FrmLogging.class);
//			frmLogging.loadInitials();
//
//			frmLogging.setVisible(true);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Billing", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;

	}

}
