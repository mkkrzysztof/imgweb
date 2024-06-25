package imageweb;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RestController
public class RectangleController {
    private List<Rectangle> rectangles = new ArrayList<>();
    @GetMapping("/rectangle")
    public Rectangle getRectangle(){
        return new Rectangle(1,1,100,100,"red");
    }
    @PostMapping("/addrectangle")
    public ResponseEntity<Void> addRectangle(@RequestBody Rectangle rectangle){
        rectangles.add(rectangle);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/listrectangles")
    public ResponseEntity<List<Rectangle>> getRectangles(){
        return ResponseEntity.ok(rectangles);
    }
    @GetMapping("/rectangles/svg")
    public ResponseEntity<String> rectanglesSvg(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<svg width=\"1000\" height=\"1000\" xmlns=\"http://www.w3.org/2000/svg\">");
        for(Rectangle i : rectangles){
            stringBuilder.append(String.format(Locale.ENGLISH,
                    "<rect width=\""
                            + i.getWidth()
                            + "\" height=\""
                            + i.getHeight()
                            + "\" x=\""
                            + i.getX()
                            + "\" y=\""
                            + i.getY()
                            + "\" fill=\""
                            + i.getColor()
                            + "\" />"));
        }
        stringBuilder.append("</svg>");
        return ResponseEntity.ok(stringBuilder.toString());
    }
    @GetMapping("/rectangles/{index}")
    public ResponseEntity<Rectangle> getRectangle(@PathVariable int index){
        if(index < 0 || index > rectangles.size()-1){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(rectangles.get(index));
    }
    @DeleteMapping("/rectangles/{index}")
    public ResponseEntity<Void> deleteRectangle(@PathVariable int index){
        if(index < 0 || index > rectangles.size()-1){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        rectangles.remove(index);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/rectangles/{index}")
    public ResponseEntity<Void> setRectangle(@PathVariable int index,@RequestBody Rectangle rectangle){
        if(index < 0 || index > rectangles.size()-1){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        rectangles.set(index,rectangle);
        return ResponseEntity.ok().build();
    }

}
