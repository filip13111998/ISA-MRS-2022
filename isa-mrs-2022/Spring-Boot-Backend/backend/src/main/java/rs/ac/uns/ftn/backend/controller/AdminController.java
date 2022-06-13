package rs.ac.uns.ftn.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.backend.dto.request.AdminLoginDTO;
import rs.ac.uns.ftn.backend.dto.request.ReportSearchDTO;
import rs.ac.uns.ftn.backend.dto.response.*;
import rs.ac.uns.ftn.backend.service.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private AdminService as;

    @Autowired
    private AdminComplaintMarkService acm;

    @Autowired
    private AdminDeleteService ads;

    @Autowired
    private ReportService rs;

    @PostMapping(value = "/acc_active/{username}",produces = "application/json")
    public CompletableFuture<ResponseEntity<Boolean>> checkAdmin(@PathVariable String username) {
        return this.as.checkUserActive(username).thenApplyAsync(ResponseEntity::ok);
    }

    @PostMapping(value = "/new_pass",produces = "application/json")
    public CompletableFuture<ResponseEntity<Boolean>> newPasswordAdmin(@RequestBody AdminLoginDTO dto) {
        return this.as.newAdminPassword(dto).thenApplyAsync(ResponseEntity::ok);
    }

    /*   OVDE TREBA PISATI KONTROLERE NA FRONTU  */
    @GetMapping(value = "/loyality",produces = "application/json")
    public CompletableFuture<ResponseEntity<LoyalityDTO>> getLoyality() {
        return  as.getLoyalityProgram().thenApplyAsync(ResponseEntity::ok);
    }

    @PostMapping(value = "/saveLoyality",produces = "application/json")
    public CompletableFuture<ResponseEntity<Boolean>> saveLoyality(@RequestBody LoyalityDTO dto) {
        return  as.saveLoyalityProgram(dto).thenApplyAsync(ResponseEntity::ok);
    }

    @GetMapping(value = "/getOneAdmin/{username}",produces = "application/json")
    public CompletableFuture<ResponseEntity<AdminProfileDTO>> getOneAdmin(@PathVariable String username) {
        return  as.getOneAdmin(username).thenApplyAsync(ResponseEntity::ok);
    }

    @PostMapping(value = "/saveAdmin",produces = "application/json")
    public CompletableFuture<ResponseEntity<Boolean>> saveAdmin(@RequestBody AdminProfileDTO a) {
        return  as.saveAdmin(a).thenApplyAsync(ResponseEntity::ok);
    }

    @PostMapping(value = "/updateAdmin",produces = "application/json")
    public CompletableFuture<ResponseEntity<Boolean>> updateAdmin(@RequestBody AdminProfileDTO a) {
        return  as.updateAdmin(a).thenApplyAsync(ResponseEntity::ok);
    }


    /*MARK-COMPLAINT*/

    /*COTTAGE*/
    @PostMapping(value = "/acceptCottageMark",produces = "application/json")
    public CompletableFuture<ResponseEntity<Boolean>> acceptCottageMark(@RequestBody MarkAdminDTO a) {
        return  acm.acceptCottageMark(a).thenApplyAsync(ResponseEntity::ok);
    }

    @PostMapping(value = "/declineCottageMark",produces = "application/json")
    public CompletableFuture<ResponseEntity<Boolean>> declineCottageMark(@RequestBody MarkAdminDTO a) {
        return  acm.declineCottageMark(a).thenApplyAsync(ResponseEntity::ok);
    }

    @GetMapping(value = "/getAllCottageMark",produces = "application/json")
    public CompletableFuture<ResponseEntity<List<ExtendMarkAdminDTO>>> getAllCottageMark() {
        return  acm.getAllCottageMark().thenApplyAsync(ResponseEntity::ok);
    }

    @PostMapping(value = "/answerCottageComplaint",produces = "application/json")
    public CompletableFuture<ResponseEntity<Boolean>> answerCottageComplaint(@RequestBody ComplaintAdminDTO a) {
        return  acm.answerCottageComplaint(a).thenApplyAsync(ResponseEntity::ok);
    }

    @GetMapping(value = "/getAllCottageComplaint",produces = "application/json")
    public CompletableFuture<ResponseEntity<List<ExtendComplaintDTO>>> getAllCottageComplaint() {
        return  acm.getAllCottageComplaint().thenApplyAsync(ResponseEntity::ok);
    }


    /*BOAT*/
    @PostMapping(value = "/accepBoatMark",produces = "application/json")
    public CompletableFuture<ResponseEntity<Boolean>> accepBoatMark(@RequestBody MarkAdminDTO a) {
        return  acm.accepBoatMark(a).thenApplyAsync(ResponseEntity::ok);
    }

    @PostMapping(value = "/declineBoatMark",produces = "application/json")
    public CompletableFuture<ResponseEntity<Boolean>> declineBoatMark(@RequestBody MarkAdminDTO a) {
        return  acm.declineBoatMark(a).thenApplyAsync(ResponseEntity::ok);
    }

    @GetMapping(value = "/getAllBoatMark",produces = "application/json")
    public CompletableFuture<ResponseEntity<List<ExtendMarkAdminDTO>>> getAllBoatMark() {
        return  acm.getAllBoatMark().thenApplyAsync(ResponseEntity::ok);
    }

    @PostMapping(value = "/answerBoatComplaint",produces = "application/json")
    public CompletableFuture<ResponseEntity<Boolean>> answerBoatComplaint(@RequestBody ComplaintAdminDTO a) {
        return  acm.answerBoatComplaint(a).thenApplyAsync(ResponseEntity::ok);
    }

    @GetMapping(value = "/getAllBoatComplaint",produces = "application/json")
    public CompletableFuture<ResponseEntity<List<ExtendComplaintDTO>>> getAllBoatComplaint() {
        return  acm.getAllBoatComplaint().thenApplyAsync(ResponseEntity::ok);
    }


    /*ADVENTURE*/
    @PostMapping(value = "/acceptAdventureMark",produces = "application/json")
    public CompletableFuture<ResponseEntity<Boolean>> acceptAdventureMark(@RequestBody MarkAdminDTO a) {
        return  acm.acceptAdventureMark(a).thenApplyAsync(ResponseEntity::ok);
    }

    @PostMapping(value = "/declineAdventureMark",produces = "application/json")
    public CompletableFuture<ResponseEntity<Boolean>> declineAdventureMark(@RequestBody MarkAdminDTO a) {
        return  acm.declineAdventureMark(a).thenApplyAsync(ResponseEntity::ok);
    }

    @GetMapping(value = "/getAllAdventureMark",produces = "application/json")
    public CompletableFuture<ResponseEntity<List<ExtendMarkAdminDTO>>> getAllAdventureMark() {
        return  acm.getAllAdventureMark().thenApplyAsync(ResponseEntity::ok);
    }

    @PostMapping(value = "/answerAdventureComplaint",produces = "application/json")
    public CompletableFuture<ResponseEntity<Boolean>> answerAdventureComplaint(@RequestBody ComplaintAdminDTO a) {
        return  acm.answerAdventureComplaint(a).thenApplyAsync(ResponseEntity::ok);
    }

    @GetMapping(value = "/getAllCAdventureComplaint",produces = "application/json")
    public CompletableFuture<ResponseEntity<List<ExtendComplaintDTO>>> getAllCAdventureComplaint() {
        return  acm.getAllCAdventureComplaint().thenApplyAsync(ResponseEntity::ok);
    }

    /*DELETE USER*/
    @GetMapping(value = "/acceptDeleteUser/{username}",produces = "application/json")
    public CompletableFuture<ResponseEntity<Boolean>> acceptDeleteUser(@PathVariable String username) {
        return  ads.acceptDeleteUser(username).thenApplyAsync(ResponseEntity::ok);
    }
    @GetMapping(value = "/declineDeleteUser/{username}",produces = "application/json")
    public CompletableFuture<ResponseEntity<Boolean>> declineDeleteUser(@PathVariable String username) {
        return  ads.declineDeleteUser(username).thenApplyAsync(ResponseEntity::ok);
    }
    @GetMapping(value = "/findAllDeletedUsers",produces = "application/json")
    public CompletableFuture<ResponseEntity<List<UserDeleteDTO>>> findAllDeletedUsers() {
        return  ads.findAllDeletedUsers().thenApplyAsync(ResponseEntity::ok);
    }


    /*COTTAGE OWNER*/

    @GetMapping(value = "/deleteCottageOwner/{id}",produces = "application/json")
    public CompletableFuture<ResponseEntity<Boolean>> deleteCottageOwner(@PathVariable Long id) {
        return  ads.deleteCottageOwner(id).thenApplyAsync(ResponseEntity::ok);
    }
    @GetMapping(value = "/findAllCottageOwner",produces = "application/json")
    public CompletableFuture<ResponseEntity<List<OwnerEntityDTO>>> findAllCottageOwner() {
        return  ads.findAllCottageOwner().thenApplyAsync(ResponseEntity::ok);
    }

    /*BOAT OWNER*/
    @GetMapping(value = "/deleteBoatOwner/{id}",produces = "application/json")
    public CompletableFuture<ResponseEntity<Boolean>> deleteBoatOwner(@PathVariable Long id) {
        return  ads.deleteBoatOwner(id).thenApplyAsync(ResponseEntity::ok);
    }
    @GetMapping(value = "/findAllBoatOwner",produces = "application/json")
    public CompletableFuture<ResponseEntity<List<OwnerEntityDTO>>> findAllBoatOwner() {
        return  ads.findAllBoatOwner().thenApplyAsync(ResponseEntity::ok);
    }

    /*INSTRUCTOR*/
    @GetMapping(value = "/deleteInstructor/{id}",produces = "application/json")
    public CompletableFuture<ResponseEntity<Boolean>> deleteInstructor(@PathVariable Long id) {
        return  ads.deleteInstructor(id).thenApplyAsync(ResponseEntity::ok);
    }
    @GetMapping(value = "/findAllInstructor",produces = "application/json")
    public CompletableFuture<ResponseEntity<List<OwnerEntityDTO>>> findAllInstructor() {
        return  ads.findAllInstructor().thenApplyAsync(ResponseEntity::ok);
    }

    /*REPORT*/
    @PostMapping(value = "/report",produces = "application/json")
    public CompletableFuture<ResponseEntity<ReportDTO>> report(@RequestBody ReportSearchDTO r) {
        return  rs.report(r).thenApplyAsync(ResponseEntity::ok);
    }

}
