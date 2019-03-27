package presentacion.reportes;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.apache.log4j.Logger;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import dto.PersonaDTO;

public class ReporteAgenda
{
	private JasperReport reporte;
	private JasperViewer reporteViewer;
	private JasperPrint	reporteLleno;
	private Logger log = Logger.getLogger(ReporteAgenda.class);
	
    public ReporteAgenda(List<PersonaDTO> personas)
    {
		Map<String, Object> parametersMap = new HashMap<String, Object>();
		parametersMap.put("Fecha", new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
		
		Collections.sort(personas, new Comparator<PersonaDTO>() {

			@Override
			public int compare(PersonaDTO persona1, PersonaDTO persona2) {
				int resultado = persona1.getLocalidad().getNombre().compareTo(persona2.getLocalidad().getNombre());
				if(resultado!=0) {
					return resultado;
				}
				return persona1.getNombre().compareTo(persona2.getNombre());
			}	
		});
		
		
    	try		{
			this.reporte = (JasperReport) JRLoader.loadObjectFromFile( "reportes" + File.separator + "ReporteAgenda.jasper" );

			this.reporteLleno = JasperFillManager.fillReport(this.reporte, parametersMap, 
					new JRBeanCollectionDataSource(personas));
    		log.info("Se cargó correctamente el reporte");
		}
		catch( JRException ex ) 
		{
			log.error("Ocurrió un error mientras se cargaba el archivo ReporteAgenda.Jasper", ex);
		}
    }       
    
    /*
     *  Reporte por localidad y ordenados alfabéticamente
		Y mostrar gráfico por cada localidad
		subtotal
     */
    public void mostrar()
	{
    	
		this.reporteViewer = new JasperViewer(this.reporteLleno,false);
		this.reporteViewer.setVisible(true);
	}
   
}	