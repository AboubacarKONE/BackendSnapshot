package com.example.backend.controller;
import com.example.backend.enumeration.ParticipantGenre;
import com.example.backend.model.Participant;
import com.example.backend.repository.ParticipantRepository;
import com.example.backend.service.ParticipantService;
import com.example.backend.service.ParticipantServiceImplement;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/odcmanager/api/v1")
@Api("odcmanager/api/v1")
@CrossOrigin("*")
public class ParticipantController {
    @Autowired
    ParticipantService participantService;
    
    @Autowired
    ParticipantRepository participantRepository;

    @PostMapping(value="/participant")
    @ApiOperation(value = "Enregistrer un participant", notes = "cette methode permet d'ajouter un participant", response = Participant.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "l'objet participant cree"),
			@ApiResponse(code = 400, message = "l'objet participant n'est pas valide") })
    public void save(@RequestBody Participant participant){
        participantService.addParticipant(participant);
        
    }
    @DeleteMapping(value = "/deleteParticipant/{id}")
    @ApiOperation(value = "supprimer un participant", notes = "cette methode permet de supprimer un participant par son id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "le participant a été supprimé"),
			@ApiResponse(code = 404, message = "aucun participant avec cet id n'existe dans la BDD") })
    public void delete (@PathVariable("id") Long id){
       participantService.deleteParticipant(id);
      
    }

    @PutMapping(path = "/participant/{id}")
    @ApiOperation(value = "Modifier un participant", notes = "cette methode permet de modifier un participant", response = Participant.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "l'objet participant modifié"),
			@ApiResponse(code = 400, message = "l'objet participant n'est pas valide") })
    public void update(@RequestBody Participant participant, @PathVariable Long id){
       participantService.updateParticipant(id,participant);
    }

    //pour afficher la liste
    @GetMapping("/participants")
    @ApiOperation(value = "renvoi la liste des particiapnt", notes = "cette methode permet de chercher et renvoyer la liste des participant qui existent"
			+ "dans la BDD", responseContainer = "list<particiapnt>")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "la liste des particiapnt / une liste vide") })
    @ResponseBody
    public List<Participant> list(){
        return participantService.listParticipant();
    }
    
    //aficher participant par son id
    @GetMapping("/participantById/{id}")
    public Participant ParticipantById(@PathVariable("id") Long id) {
		return participantService.ParticipantById(id);
	}
    
    @PostMapping("/uploadexcel")
    public List<Participant> importExcelFile(@RequestParam("file") MultipartFile files) throws IOException, IOException {
        List<Participant> participants = new ArrayList<>();
        System.out.println(files);

        XSSFWorkbook workbook = new XSSFWorkbook(files.getInputStream());

        // lecture du fichier excel
        XSSFSheet worksheet = workbook.getSheetAt(0);
        for (int index = 0; index < worksheet.getPhysicalNumberOfRows(); index++) {
            if (index > 0) {
                XSSFRow row = worksheet.getRow(index);
                Participant p = new Participant();

                p.setNom_complet(getCellValue(row, 0));
                p.setTelephone(convertStringToInt(getCellValue(row, 1)));  ;
                p.setStructure(getCellValue(row, 2));
                p.setDomaine(getCellValue(row, 3));
                p.setEmail(getCellValue(row, 4));
                p.setParticipantGenre(ParticipantGenre.valueOf(getCellValue(row, 5)));
                participantService.addParticipant(p);
                System.out.println(p);
            }
        }

        return participants;
    }


    private String getCellValue(Row row, int cellNo) {
        DataFormatter formatter = new DataFormatter();

        Cell cell = row.getCell(cellNo);

        return formatter.formatCellValue(cell);
    }

    private int convertStringToInt(String str) {
        int result = 0;
        if (str == null || str.isEmpty() || str.trim().isEmpty()) {
            return result;
        }
        result = Integer.parseInt(str);
        return result;
    }
    @GetMapping("/participantGenre={genre}")
    public int findByparticipantGenre(@PathVariable("genre") ParticipantGenre genre){
    	return participantService.findByparticipantGenre(genre);
    }
}
