/**
 * Created by nektodev on 23.08.15.
 */

// Models
window.Artist = Backbone.Model.extend();

window.ArtistCollection = Backbone.Collection.extend({
    model: Artist,
    url:"http://localhost:8080/api/artist"
});

// Views
window.ArtistListView = Backbone.View.extend({

    tagName:'ul',

    initialize:function () {
        this.model.bind("reset", this.render, this);
    },

    render:function (eventName) {
        _.each(this.model.models, function (artist) {
            $(this.el).append(new ArtistListItemView({model:artist}).render().el);
        }, this);
        return this;
    }

});

window.ArtistListItemView = Backbone.View.extend({

    tagName:"li",

    template:_.template($('#tpl-artist-list-item').html()),

    render:function (eventName) {
        $(this.el).html(this.template(this.model.toJSON()));
        return this;
    }

});

window.ArtistView = Backbone.View.extend({

    template:_.template($('#tpl-artist-details').html()),

    render:function (eventName) {
        $(this.el).html(this.template(this.model.toJSON()));
        return this;
    }

});

// Router
var AppRouter = Backbone.Router.extend({

    routes:{
        "":"list",
        "artist/:id":"artistDetails"
    },

    list:function () {
        var artistList = new ArtistCollection();
        artistList.fetch({
            success: function(){
                this.artistListView = new ArtistListView({model:artistList});
                $('#sidebar').html(this.artistListView.render().el);
            }
        });

    },

    artistDetails:function (id) {
        this.artist = artistList.get(id);
        this.artistView = new ArtistView({model:this.artist});
        $('#content').html(this.artistView.render().el);
    }
});



$(document).ready(function() {
    $.material.init();
    var app = new AppRouter();
    Backbone.history.start();
});
